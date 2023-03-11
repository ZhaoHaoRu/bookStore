package org.sjtu.backend.daoimpl;

import org.sjtu.backend.dao.CartDao;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.Cart;
import org.sjtu.backend.entity.CartItem;
import org.sjtu.backend.entity.User;
import org.sjtu.backend.repository.BookRepository;
import org.sjtu.backend.repository.CartItemRepository;
import org.sjtu.backend.repository.CartRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao{
    @Resource
    private CartRepository cartRepository;
    @Resource
    private CartItemRepository cartItemRepository;
    @Resource
    private BookRepository bookRepository;

    @Override
    public Cart create(Cart cart){
        return cartRepository.save(cart);
    }

    @Override
    public Cart findByUser(User user) { return cartRepository.findByOwner(user); }

    @Override
//    public Cart findByUser(String username) {
//        User user = userRepository.findByName(username);
//        if(user == null)
//            return null;
//        Cart cart = cartRepository.findByOwner(user);
//        if(cart == null)
//            return null;
//
//    }
    public Cart deleteCartItem(Book book, Cart cart) {
        CartItem cartItem = cartItemRepository.findByBookAndCartId(book, cart);
        if(cartItem != null){
            cartItemRepository.deleteById(cartItem.getId());
        }
        return cart;
    }

    @Override
    public CartItem findCartItem(Book book, Cart cart) {
        CartItem cartItem = cartItemRepository.findByBookAndCartId(book, cart);
        return cartItem;
    }

    @Override
    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCartId(cart);
    }

    @Override
    public List<CartItem> findByBook(Book book) {
        return cartItemRepository.findByBook(book);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

//    @Override
//    public List<CartItem> deleteAllByCartId(Cart cart) {
//        return cartItemRepository.deleteAllByCartId(cart);
//    }

}
