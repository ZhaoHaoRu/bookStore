package org.sjtu.backend.serviceimpl;

import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.dao.CartDao;
import org.sjtu.backend.dao.OrderDao;
import org.sjtu.backend.dao.UserDao;
import org.sjtu.backend.entity.*;
import org.sjtu.backend.service.BookService;
import org.sjtu.backend.service.CartService;
import org.sjtu.backend.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private CartDao cartDao;

    @Resource
    private UserDao userDao;

    @Resource
    private BookDao bookDao;

    @Resource
    CartService cartService;

    @Autowired
    BookService bookService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public Order generateOrder(String username, String recipient, String phone, String address) {
        // 生成订单
        // 书籍库存要相应减少
        // 如果书籍的数量不够，要对于用户有相应的提示，正常生成订单
        // 清空购物车
        /**
         * exception
         */
//        int result = 1 / 0;
        User user = userDao.findByName(username);
        Cart cart = cartDao.findByUser(user);
        Order newOrder = new Order();
        int flag = 0;
        newOrder.setBuyer(user);
        if(recipient == null) {
            newOrder.setRecipient(username);
        }
        else
            newOrder.setRecipient(recipient);
        if(phone == null) {
            newOrder.setPhone(user.getPhone());
        }
        else
            newOrder.setPhone(phone);
        if (address == null) {
            newOrder.setAddress(null);
        }
        else
            newOrder.setAddress(address);
        Date newDate = new Date();
        Timestamp t = new Timestamp(newDate.getTime());
        newOrder.setAddDate(t);
        Order order = orderDao.create(newOrder);
        if (order == null)
            return null;
        List<CartItem> cartItems = cartDao.findByCart(cart);
        for(CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            Book toBuy = cartItem.getBook();
            int count = cartItem.getCount();
            orderItem.setBook(toBuy);
            orderItem.setCount(count);
            if(toBuy.getInventory() >= count) {
                toBuy.setInventory(toBuy.getInventory() - count);
                toBuy.setSales(toBuy.getSales() + count);
            }
            else {
                toBuy.setInventory(0);
                flag = 1;
                toBuy.setSales(toBuy.getInventory() + toBuy.getSales());
            }
            orderItem.setOrderId(order);
//            List<OrderItem> orderItems = order.getOrderItemList();
//            OrderItem orderItemSaved = orderDao.save(orderItem);
//            orderItems.add(orderItemSaved);
//            order.setOrderItemList(orderItems);
            try {
                orderDao.save(orderItem);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * exception
         */
//        int result = 1 / 0;
        cartService.clearCart(username);
        if(flag == 1)
            order.setId(0);
        return order;
    }

    @Override
    public List<Order> showAllOrderOfUser(String username) {
        User user = userDao.findByName(username);
        return orderDao.findByBuyer(user);
    }

    @Override
    public List<List<OrderItem>>  showAllOrder() {
        List<Order> orders = orderDao.findAll();
        List<List<OrderItem>> selected = new ArrayList<List<OrderItem>>();
        int len = selected.size();
        for(Order order : orders){
            List<OrderItem> orderItems = orderDao.findByOrderId(order);
            selected.add(orderItems);
        }
//        Collections.sort(selected, Collections.reverseOrder());
//        Collections.reverse(selected);
        return selected;
    }

    @Override
    public List<Order> showOrderOfUserByBook(String username, String name) {
        User user = userDao.findByName(username);
        List<Book> books = bookDao.findByName(name);
        List<Order> orders = orderDao.findByBuyer(user);
        List<Order> selected = new ArrayList<Order>();
        for(Order order : orders) {
            for(Book book : books) {
                OrderItem orderItem = orderDao.findByBookAndOrderId(book, order);
                if(orderItem != null) {
                    selected.add(order);
                    break;
                }
            }
        }
        return selected;
    }

    @Override
    public List<Order> showOrderByBook(String name) {
        List<Order> orders = orderDao.findAll();
        List<Book> books = bookDao.findByName(name);
        List<Order> orderList = new ArrayList<>();
        for(Order order : orders) {
            for(Book book : books) {
                OrderItem orderItem = orderDao.findByBookAndOrderId(book, order);
                if(orderItem != null) {
                    orderList.add(order);
                    break;
                }
            }
        }
        return orderList;
    }

    @Override
    public List<OrderItem> getOneOrder(int OrderId) {
        Order order = orderDao.findByOrder(OrderId);
        List<OrderItem> orderItems = orderDao.findByOrderId(order);
        return orderItems;
    }

    @Override
    public List<OrderItem> getLatestOrder(String username) {
        User user = userDao.findByName(username);
        List<Order> orders = orderDao.findByBuyer(user);
        if(orders.size() > 0) {
            Order order = orders.get(orders.size() - 1);
            List<OrderItem> orderItems = orderDao.findByOrderId(order);
            return orderItems;
        } else {
            return null;
        }
    }

    @Override
    //logic here only displays the books that have been bought
    public List<Book> showBestSeller(int dateNum) {
        List<Order> orders = new ArrayList<>();
        if(dateNum == 0)
            orders = orderDao.findAll();
        else{
            Long endTime = System.currentTimeMillis();
            Long miss = Long.valueOf(dateNum * 24 * 60 * 60 * 1000L);
            Long startTime = endTime - miss;
            System.out.println(new Timestamp(endTime));
            Timestamp begin = new Timestamp(startTime);
            orders = orderDao.findByAddDateAfter(begin);
        }
        Set<Book> sellBook = new HashSet();
        for(Order order : orders){
            List<OrderItem> orderItems = orderDao.findByOrderId(order);
            for(OrderItem orderItem: orderItems){
                Book book = orderItem.getBook();
                if(sellBook.contains(book))
                    continue;
                else
                    sellBook.add(book);
            }
        }
        List<Book> bookList =new ArrayList(sellBook);
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b2.getSales() - b1.getSales();
            }
        });
        return bookList;
    }

    @Override
    public List<User> showCustomers(int dateNum) {
        List<Order> orders;
        if(dateNum == 0)
            orders = orderDao.findAll();
        else{
            Long endTime = System.currentTimeMillis();
            Long miss = Long.valueOf(dateNum * 24 * 60 * 60 * 1000L);
            Long startTime = endTime - miss;
            System.out.println(new Timestamp(endTime));
            Timestamp begin = new Timestamp(startTime);
            orders = orderDao.findByAddDateAfter(begin);
        }
        Set<User> customers = new HashSet<>();
        for(Order order : orders){
            double pay;
            int payYuan = 0;
            int payJiao = 0;
            List<OrderItem> orderItems = orderDao.findByOrderId(order);
            for(OrderItem orderItem : orderItems) {
                payYuan += orderItem.getCount() * orderItem.getBook().getPriceYuan();
                payJiao += orderItem.getCount() * orderItem.getBook().getPriceJiao();
            }
            pay = payYuan + payJiao / 100.0;
            User buyer = order.getBuyer();
            if(!customers.isEmpty() && customers.contains(buyer)){
                buyer.setConsumption(buyer.getConsumption() + pay);
            }
            else {
               buyer.setConsumption(pay);
               customers.add(buyer);
            }
        }
        List<User> customerList =new ArrayList(customers);
        Collections.sort(customerList, new Comparator<User>() {
            @Override
            public int compare(User b1, User b2) {
                return (int)(b2.getConsumption() - b1.getConsumption());
            }
        });
        return customerList;
    }

    @Override
    public List<Book> showCustomerBuy(String username, int dateNum) {
        User user = userDao.findByName(username);
        Set<Book> boughtBook = new HashSet<>();
        // first:bookId, second:sales
        Map<Integer, Integer> countBook = new HashMap<>();
        List<Order> orders;
        List<Book> books = new ArrayList<>();
        if(dateNum == 0)
            orders = orderDao.findByBuyer(user);
        else{
            Long endTime = System.currentTimeMillis();
            Long miss = Long.valueOf(dateNum * 24 * 60 * 60 * 1000L);
            Long startTime = endTime - miss;
            Timestamp begin = new Timestamp(startTime);
            orders = orderDao.findByBuyerAndAddDateAfter(user, begin);
        }
        for(Order order : orders){
            List<OrderItem> orderItems = orderDao.findByOrderId(order);
            for(OrderItem orderItem: orderItems){
                Book book = orderItem.getBook();
                if(countBook.containsKey(book.getId())){
                    countBook.put(book.getId(), countBook.get(book.getId()) + orderItem.getCount());
                }
                else
                    countBook.put(book.getId(), orderItem.getCount());
            }
        }
        for (Map.Entry<Integer, Integer> entry : countBook.entrySet()) {
            int oldId = entry.getKey();
            Book old = bookDao.findById(oldId);
            int oldInventory = old.getInventory();
            Book newBook = bookService.copyBook(old);
            old.setInventory(oldInventory);
            newBook.setSales(entry.getValue());
            books.add(newBook);
        }
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b2.getSales() - b1.getSales();
            }
        });
        return books;
    }

    @Override
    public boolean queryOrder(String username, String recipient, String phone, String address) {
        User user = userDao.findByName(username);
        // check the user
        if(user == null) {
            return false;
        }
        // 查找信息相符的订单
        List<Order> orders = orderDao.findSpecificOrder(user, recipient, phone, address);
        if(orders == null) {
            return false;
        }
        // 确定时间最近的订单是否在1min内
        Order order = orders.get(orders.size() - 1);
        Long endTime = System.currentTimeMillis();
        Long miss = Long.valueOf( 60 * 1000L);  // check within 1 min
        Long startTime = endTime - miss;
        Timestamp begin = new Timestamp(startTime);
        Timestamp thisTime = order.getAddDate();
        if(begin.before(thisTime)) {
            return true;
        }
        return false;
    }

}
