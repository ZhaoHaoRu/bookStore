package org.sjtu.backend.dao;

import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.Cart;
import org.sjtu.backend.entity.CartItem;
import org.sjtu.backend.entity.User;

import java.util.List;
public interface CartDao {
     Cart create(Cart cart);

     Cart findByUser(User user);

     Cart deleteCartItem(Book book, Cart cart);

     CartItem findCartItem(Book book, Cart cart);

     List<CartItem> findByCart(Cart cart);

     List<CartItem> findByBook(Book book);

     CartItem save(CartItem cartItem);

//     List<CartItem> deleteAllByCartId(Cart cart);

     //还需不需要一个把所有某一个人的购物车里的东西全部搜出来的函数啊？
}
