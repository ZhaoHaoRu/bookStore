package org.sjtu.backend.repository;


import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.Cart;
import org.sjtu.backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
    CartItem findByBookAndCartId(Book book, Cart cartId);

    List<CartItem> findByCartId(Cart cart);

    CartItem findById(int id);

    List<CartItem> findByBook(Book book);

    CartItem save(CartItem cartItem);

    CartItem deleteById(int id);

    void deleteAll();

    List<CartItem> deleteAllByCartId(Cart cart);

    List<CartItem> findAll();
}
