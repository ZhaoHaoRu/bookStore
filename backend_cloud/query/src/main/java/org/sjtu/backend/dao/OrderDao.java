package org.sjtu.backend.dao;

import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.Order;
import org.sjtu.backend.entity.OrderItem;
import org.sjtu.backend.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
    Order create(Order order);

    Order findByOrder(int id);

    List<Order> findByBuyer(User buyer);

    List<OrderItem> findByOrderId(Order order);

    OrderItem findById(int id);

    OrderItem save(OrderItem orderItem);

    List<Order> findAll();

    OrderItem findByBookAndOrderId(Book book, Order order);

    List<Order> findByAddDateAfter(Timestamp begin);

    List<Order> findByBuyerAndAddDateAfter(User buyer, Timestamp begin);

    List<Order> findSpecificOrder(User buyer, String recipient, String phone, String address);
}
