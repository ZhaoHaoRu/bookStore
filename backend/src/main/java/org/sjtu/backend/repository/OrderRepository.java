package org.sjtu.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sjtu.backend.entity.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

public interface OrderRepository extends JpaRepository<Order,Integer>{
    List<Order> findByBuyerOrderByAddDate(User user);

    Order save(Order order);

    List<Order> findByBuyer(User buyer);

    Order findById(int id);

    List<Order> findByAddDateAfter(Timestamp begin);

    List<Order> findByBuyerAndAddDateAfter(User buyer, Timestamp begin);

    List<Order> findByBuyerAndRecipientAndPhoneAndAddress(User buyer, String recipient, String phone, String address);

}
