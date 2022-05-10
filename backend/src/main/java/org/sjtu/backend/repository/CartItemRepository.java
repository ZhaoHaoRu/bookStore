package org.sjtu.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sjtu.backend.entity.*;
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
