package org.sjtu.backend.repository;

import org.sjtu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//重名问题？
public interface UserRepository extends JpaRepository<User,Integer>{
    @Query(value = "from User where name = :username and passward = :password")
    User checkUser(@Param("username") String username, @Param("password") String password);

    User findByName(String name);

    void deleteByName(String name);

    User findById(int id);

    List<User> findAll();

    @Query(value = "from User where name = :username and isAdministrators = 1")
    User checkAdministrators(@Param("username") String username);

    @Query(value = "from User where name = :username and isBan = 1")
    User checkBan(@Param("username") String username);

    List<User> findAllByIsAdministrators(int isAdministrators);
}
