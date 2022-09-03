package org.sjtu.backend.serviceimpl;

import org.sjtu.backend.entity.*;
import org.sjtu.backend.dao.*;
import org.sjtu.backend.service.CartService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CartServiceImpl implements CartService{
    @Resource
    private UserDao userDao;

    @Resource
    private CartDao cartDao;

    @Resource
    private BookDao bookDao;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<CartItem> findByUser(String username) {
        User user = userDao.findByName(username);
        Cart cart = cartDao.findByUser(user);
        List<CartItem> cartItems = cartDao.findByCart(cart);
        return cartItems;
    }

    @Override
    public Cart addToCart(int bookId, String username) {
        User user = userDao.findByName(username);
        if(user == null)
            return null;
        Cart cart = cartDao.findByUser(user);
        if(cart == null)
            return null;
        Book book = bookDao.findById(bookId);
        CartItem cartItem = cartDao.findCartItem(book, cart);
        if(cartItem != null){
            cartItem.setCount(cartItem.getCount() + 1);
            Date newDate = new Date();
            Timestamp t = new Timestamp(newDate.getTime());
            cartItem.setAddDate(t);
            cartDao.save(cartItem);
        }
        else{
            CartItem newCartItem = new CartItem();
            newCartItem.setCount(1);
            newCartItem.setCartId(cart);
            newCartItem.setBook(book);
            Date newDate = new Date();
            Timestamp t = new Timestamp(newDate.getTime());
            newCartItem.setAddDate(t);
            cartDao.save(newCartItem);
        }
        return cart;
    }

    @Override
    public Cart deleteToCart(int bookId, String username) {
        User user = userDao.findByName(username);
        if(user == null)
            return null;
        Cart cart = cartDao.findByUser(user);
        if(cart == null)
            return null;
        Book book = bookDao.findById(bookId);
        CartItem cartItem = cartDao.findCartItem(book, cart);
        if(cartItem != null){
            cartItem.setCount(cartItem.getCount() - 1);
            if(cartItem.getCount() == 0){
                return cartDao.deleteCartItem(book, cart);
            }
            Date newDate = new Date();
            Timestamp t = new Timestamp(newDate.getTime());
            cartItem.setAddDate(t);
            cartDao.save(cartItem);
        }
        else
            return null;
        return cart;
    }

    @Override
    public Cart clearCart(String username) {
        User user = userDao.findByName(username);
        if(user == null)
            return null;
        Cart cart = cartDao.findByUser(user);
        if(cart == null)
            return null;
        List<CartItem> cartItems = cartDao.findByCart(cart);
        for(CartItem cartItem : cartItems) {
            Book toDelete = cartItem.getBook();
            cartDao.deleteCartItem(toDelete, cart);
        }
        return cart;
    }

    @Override
    public List<CartItem> updateBookInCart(int oldId, int newId) {
        Book oldBook = bookDao.findById(oldId);
        Book newBook = bookDao.findById(newId);
        if(oldBook == null || newBook == null)
            return null;
        List<CartItem> cartItems = cartDao.findByBook(oldBook);
        for(CartItem cartItem : cartItems){
            cartItem.setBook(newBook);
            cartDao.save(cartItem);
        }
        return cartItems;
    }

    @Override
    public Cart changeBookCount(int bookId, String username, int count) {
        if(count < 0)
            return null;
        User user = userDao.findByName(username);
        if(user == null)
            return null;
        Cart cart = cartDao.findByUser(user);
        if(cart == null)
            return null;
        Book book = bookDao.findById(bookId);
        CartItem cartItem = cartDao.findCartItem(book, cart);
        if(cartItem != null){
            cartItem.setCount(count);
            if(cartItem.getCount() == 0){
                return cartDao.deleteCartItem(book, cart);
            }
            Date newDate = new Date();
            Timestamp t = new Timestamp(newDate.getTime());
            cartItem.setAddDate(t);
            cartDao.save(cartItem);
        }
        else
            return null;
        return cart;
    }

}
