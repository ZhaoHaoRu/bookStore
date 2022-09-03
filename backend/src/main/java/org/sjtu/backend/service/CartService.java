package org.sjtu.backend.service;

import org.sjtu.backend.dao.UserDao;
import org.sjtu.backend.entity.*;

import java.util.List;

public interface CartService {
    List<CartItem> findByUser(String username);

    Cart addToCart(int bookId, String username);

    Cart clearCart(String username);

    Cart deleteToCart(int bookId, String username);

    List<CartItem> updateBookInCart(int oldId, int newId);

    Cart changeBookCount(int bookId, String username, int count);
}
