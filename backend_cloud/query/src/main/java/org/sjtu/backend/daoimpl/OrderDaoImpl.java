package org.sjtu.backend.daoimpl;

import org.sjtu.backend.dao.OrderDao;
import org.sjtu.backend.entity.*;
import org.sjtu.backend.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order create(Order order) {
        /**
         * exception
         */
//        int result = 1 / 0;
        return orderRepository.save(order);
    }

    @Override
    public  Order findByOrder(int id) { return orderRepository.findById(id); }

    @Override
    public List<Order> findByBuyer(User buyer) {
        return orderRepository.findByBuyer(buyer);
    }

    @Override
    public List<OrderItem> findByOrderId(Order order) {
        return orderItemRepository.findByOrderId(order);
    }

    @Override
    public OrderItem findById(int id) {
        return orderItemRepository.findById(id);
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem) {
        /**
         * exception
         */
//        int result = 1 / 0;
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderItem findByBookAndOrderId(Book book, Order order) {
        return orderItemRepository.findByBookAndOrderId(book, order);
    }

    @Override
    public List<Order> findByAddDateAfter(Timestamp begin) {
        return orderRepository.findByAddDateAfter(begin);
    }

    @Override
    public List<Order> findByBuyerAndAddDateAfter(User buyer, Timestamp begin) {
        return orderRepository.findByBuyerAndAddDateAfter(buyer, begin);
    }

    @Override
    public List<Order> findSpecificOrder(User buyer, String recipient, String phone, String address) {
        return orderRepository.findByBuyerAndRecipientAndPhoneAndAddress(buyer, recipient, phone, address);
    }
}
