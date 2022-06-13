package org.sjtu.backend.controllers;


import org.sjtu.backend.entity.*;
import org.sjtu.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/order/generateOrder")
    public Order generateOrder(@RequestParam("username") String username, @RequestParam("recipient") String recipient,
                               @RequestParam("phone") String phone, @RequestParam("address") String address)
    {
        return orderService.generateOrder(username, recipient, phone, address);
    }


    @RequestMapping("/order/showOrder")
    public List<List<OrderItem>> showOrder(@RequestParam("username") String username) {
        if(userService.checkAdministrators(username) == false) {
            List<Order> orders = orderService.showAllOrderOfUser(username);
            int lens = orders.size();
            List result = new ArrayList<List<OrderItem>>();
            for(Order order : orders){
                List<OrderItem> orderItems = orderService.getOneOrder(order.getId());
                result.add(orderItems);
            }
            return result;
        }
        else {
            return orderService.showAllOrder();
        }
    }


    @RequestMapping("/order/showOrderOfBook")
    public List<Order> showOrderOfBook(@RequestParam("username") String username, @RequestParam("name") String name) {
        if(userService.checkAdministrators(username) == false) {
            List<Order> orders = orderService.showOrderOfUserByBook(username, name);
            return orders;
        }
        else
            return orderService.showOrderByBook(name);
    }


    @RequestMapping("order/getOneOrder")
    public List<OrderItem> getOneOrder(@RequestParam("orderId") Integer orderId) {
        List<OrderItem> orderItems = orderService.getOneOrder(orderId);
        return orderItems;
    }


    @RequestMapping("order/getLatestOrder")
    public List<OrderItem> getLatestOrder(@RequestParam("username") String username) {
        List<OrderItem> orderItems = orderService.getLatestOrder(username);
        return orderItems;
    }


    @RequestMapping("order/showBestSeller")
    public List<Book> showBestSeller(@RequestParam("dateNum") Integer dateNum) {
        System.out.println(dateNum);
        List<Book> books = orderService.showBestSeller(dateNum);
        return books;
    }


    @RequestMapping("order/showCustomers")
    public List<User>  showCustomers(@RequestParam("dateNum") Integer dateNum) {
        List<User> customers = orderService.showCustomers(dateNum);
        return customers;
    }


    @RequestMapping("order/showCustomerBuy")
    public List<Book> showCustomerBuy(@RequestParam("username") String username, @RequestParam("dateNum") Integer dateNum) {
        List<Book> books = orderService.showCustomerBuy(username, dateNum);
        return books;
    }
}
