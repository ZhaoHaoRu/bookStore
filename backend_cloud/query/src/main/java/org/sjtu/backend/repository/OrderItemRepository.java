package org.sjtu.backend.repository;


import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.Order;
import org.sjtu.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
    List<OrderItem> findByBook(Book book);

    List<OrderItem> findByOrderId(Order order);

    OrderItem findById(int id);

    OrderItem save(OrderItem orderItem);

    OrderItem deleteAllByOrderId(Order order);

    OrderItem findByBookAndOrderId(Book book, Order order);
}
