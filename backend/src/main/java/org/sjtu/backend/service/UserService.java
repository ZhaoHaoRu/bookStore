package org.sjtu.backend.service;

import org.sjtu.backend.dao.UserDao;
import org.sjtu.backend.entity.User;

import java.util.List;

public interface UserService {

    User register(String username, String password, String email, String phone, String address);

    List<User> findAll();

    User findByUsername(String username);

    User updateInfo(String username, String address, String phone, String email);

    User changePassword(String username, String newPassword);

    User checkUser(String username, String password);

    void deleteUser(String username);

    boolean checkAdministrators(String username);

    User banUser(int userId);

    //底下应该有个release user
}
