package org.sjtu.backend.service;

import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.Order;
import org.sjtu.backend.entity.OrderItem;
import org.sjtu.backend.entity.User;

import java.util.List;

public interface OrderService {
    //这里默认如果recipient == empty的话，说明采用user默认的信息
    Order generateOrder(String username, String recipient, String phone, String address);

    List<Order> showAllOrderOfUser(String username);

    List<List<OrderItem>> showAllOrder();

    List<Order> showOrderOfUserByBook(String username, String name);

    List<Order> showOrderByBook(String name);

    List<OrderItem> getOneOrder(int OrderId);

    List<OrderItem> getLatestOrder(String username);

    List<Book> showBestSeller(int dateNum);

    List<User> showCustomers(int dateNum);

    List<Book> showCustomerBuy(String username, int dateNum);

    boolean queryOrder(String username, String recipient, String phone, String address);
}
