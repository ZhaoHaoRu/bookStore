package org.sjtu.backend.serviceimpl;

import org.sjtu.backend.dao.CartDao;
import org.sjtu.backend.dao.UserDao;
import org.sjtu.backend.entity.Cart;
import org.sjtu.backend.entity.User;
import org.sjtu.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    @Resource
    private CartDao cartDao;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User register(String username, String password, String email, String phone
//                         String address
    ){
        User tmp = findByUsername(username);
        if(tmp != null) {
            tmp.setName(null);
            return tmp;
        }
        User user = new User();
        user.setName(username);
        user.setPassward(password);
//        user.setAddress("null");
        user.setEmail(email);
        user.setPhone(phone);
//        user.setAddress(address);
        user.setIsAdministrators(0);
        user.setIsBan(0);

        try {
            User newUser = userDao.save(user);
            int ownerId = newUser.getId();
            Cart newCart = new Cart();
            newCart.setOwner(newUser);
            Cart cart = cartDao.create(newCart);
            return newUser;
        }
        catch(Exception e){
            logger.debug("Register Error:"+e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByName(username);
    }

    @Override
    public User updateInfo(String username, String phone, String email){
        User toUpdate = userDao.findByName(username);
        if(toUpdate == null)
            return null;
//        toUpdate.setAddress(address);
        toUpdate.setPhone(phone);
        toUpdate.setEmail(email);
        return userDao.save(toUpdate);
    }

    @Override
    public User changePassword(String username, String newPassword) {
        User toUpdate = userDao.findByName(username);
        if(toUpdate == null)
            return null;
        toUpdate.setPassward(newPassword);
        return userDao.save(toUpdate);
    }

    @Override
    public User checkUser(String username, String password){
//        if(userDao.checkBan(username) != null)
//            return null;
        return userDao.checkUser(username, password);
    }


    @Override
    public void deleteUser(String username){
        userDao.deleteByName(username);
    }

    @Override
    public boolean checkAdministrators(String username){
        User user = userDao.checkAdministrators(username);
        if(user == null)
            return false;
        else
            return true;

    }

    //在第二次触发的时候就是解除封禁
    @Override
    public User banUser(int userId){
        User toBan = userDao.findById(userId);
        //如果用户不存在或者被禁止的用户也是管理员，直接返回
        if(toBan == null || checkAdministrators(toBan.getName()))
            return null;
        User user = userDao.checkBan(toBan.getName());
        boolean isBan = true;
        if(user != null)
            isBan = false;
        User baned = userDao.banUser(toBan.getName(), isBan);
        return baned;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userDao.findAllUser();
        return users;
    }
}
