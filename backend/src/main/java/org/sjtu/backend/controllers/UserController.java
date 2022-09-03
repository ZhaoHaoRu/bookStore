package org.sjtu.backend.controllers;


import net.sf.json.JSONObject;
import org.sjtu.backend.entity.User;
import org.sjtu.backend.service.UserService;
import org.sjtu.backend.utils.msgutils.*;
import org.sjtu.backend.utils.sessionutils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/user/checkUser")
    public User checkUser(@RequestParam("username") String username,@RequestParam("password") String password){
        logger.debug("get checkUser!");
        return userService.checkUser(username, password);
    }


    @RequestMapping("/register")
    public Msg register(@RequestParam("username") String username,@RequestParam("password") String password,
                         @RequestParam("email") String email, @RequestParam("phone") String phone
//                        @RequestParam("address") String address
    ){
        logger.debug("get register!");
        System.out.println("get here!");
//        User user = userService.register(username, password, email, phone, address);
        User user = userService.register(username, password, email, phone);
        if(user == null)
            return MsgUtil.makeMsg(MsgUtil.ERROR, MsgUtil.REGISTER_ERROR_MSG, null);
        if(user.getName() == null)
            return MsgUtil.makeMsg(MsgUtil.REGISTER_ERROR, MsgUtil.REGISTER_NAME_MSG, null);
        else
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG, JSONObject.fromObject(user));
    }


    @RequestMapping("/user/update")
    public User updateUserInfo(@RequestParam("username") String username, @RequestParam("email") String email,
                               @RequestParam("phone") String phone
//                               @RequestParam("address") String address
                               )
    {
        return userService.updateInfo(username, phone, email);
    }


    @RequestMapping("/user/changePassword")
    public User changePassword(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return userService.changePassword(username, password);
    }


    @RequestMapping("/admin/banUser")
    public User banUser(@RequestParam("userId") Integer userId)
    {
        return userService.banUser(userId);
    }

    @RequestMapping("/user/getAllUser")
    public List<User> getAllUSer() { return userService.getAllUser(); }
}
