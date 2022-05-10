package org.sjtu.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sjtu.backend.entity.*;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
    List<OrderItem> findByBook(Book book);

    List<OrderItem> findByOrderId(Order order);

    OrderItem findById(int id);

    OrderItem save(OrderItem orderItem);

    OrderItem deleteAllByOrderId(Order order);

    OrderItem findByBookAndOrderId(Book book, Order order);
}
