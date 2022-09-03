package org.sjtu.backend.controllers;

import org.sjtu.backend.entity.*;
import org.sjtu.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/cart/addToCart")
    public List<CartItem> addToCart(@RequestParam("bookId") Integer bookId , @RequestParam("username") String username)
    {
        Cart cart = cartService.addToCart(bookId, username);
        List<CartItem> cartItems = cartService.findByUser(username);
        System.out.println(cartItems);
        return cartItems;
    }

    @RequestMapping("/cart/deleteToCart")
    public Cart deleteToCart(@RequestParam("bookId") Integer bookId , @RequestParam("username") String username)
    {
        return cartService.deleteToCart(bookId, username);
    }

    @RequestMapping("/cart/changeBookCount")
    public Cart deleteToCart(@RequestParam("bookId") Integer bookId , @RequestParam("username") String username, @RequestParam("count") Integer count)
    {
        return cartService.changeBookCount(bookId, username, count);
    }

    @RequestMapping("/cart/clearCart")
    public Cart clearCart(@RequestParam("username") String username) {
        return cartService.clearCart(username);
    }

    @RequestMapping("/cart/displayCart")
    public List<CartItem>  displayCart(@RequestParam("username") String username) {
        List<CartItem> cartItems = cartService.findByUser(username);
        System.out.println(cartItems);
        return cartItems;
    }
}
