package org.sjtu.backend.repository;


import org.sjtu.backend.entity.Cart;
import org.sjtu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer>{
    Cart findByOwner(User owner);

    Cart findById(int id);
}
