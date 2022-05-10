package org.sjtu.backend.daoimpl;

import org.sjtu.backend.entity.User;
import org.sjtu.backend.dao.UserDao;
import org.sjtu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password){
        return userRepository.checkUser(username, password);
    }

    @Override
    public User findByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    public void deleteByName(String name){
        userRepository.deleteByName(name);
    }

    @Override
    public User findById(int id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User checkAdministrators(String username){
        return userRepository.checkAdministrators(username);
    }

    @Override
    public User banUser(String username, boolean isBan) {
        User user = findByName(username);
        if(user == null)
            return null;
        if(isBan)
            user.setIsAdministrators(2);
        else
            user.setIsAdministrators(0);
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User checkBan(String username){
        return userRepository.checkBan(username);
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }
}
