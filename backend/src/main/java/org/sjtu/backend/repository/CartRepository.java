package org.sjtu.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sjtu.backend.entity.*;

public interface CartRepository extends JpaRepository<Cart,Integer>{
    Cart findByOwner(User owner);

    Cart findById(int id);
}
