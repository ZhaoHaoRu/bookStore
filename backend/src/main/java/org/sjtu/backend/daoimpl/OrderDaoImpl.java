package org.sjtu.backend.daoimpl;

import org.sjtu.backend.entity.*;
import org.sjtu.backend.dao.OrderDao;
import org.sjtu.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.sql.Timestamp;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    public Order create(Order order) {
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
    public OrderItem save(OrderItem orderItem) {
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
}
