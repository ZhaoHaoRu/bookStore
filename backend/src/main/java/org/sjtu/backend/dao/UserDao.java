package org.sjtu.backend.dao;

import org.sjtu.backend.entity.User;

import java.util.List;

public interface UserDao {
    User checkUser(String username, String password);

    User findByName(String name);

    void deleteByName(String name);

    User findById(int id);

    List<User> findAll();

    User checkAdministrators(String username);

    User banUser(String username, boolean isBan);

    User checkBan(String username);

    User save(User user);
}
