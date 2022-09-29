package org.sjtu.backend.controllers;

import org.sjtu.backend.entity.*;
import org.sjtu.backend.constant.Constant;
import org.sjtu.backend.service.TimeService;
import org.sjtu.backend.service.UserService;
import org.sjtu.backend.utils.msgutils.*;
import org.sjtu.backend.utils.sessionutils.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @Resource
    private TimeService timeService;


    /**
     * @Description: login
     * @Param: username, password, remember
     * @return: Msg
     */

    @RequestMapping("/login")
    //public Msg login(@RequestParam(Constant.USERNAME) String username, @RequestParam(Constant.PASSWORD) String password, @RequestParam(Constant.REMEMBER_ME) Boolean remember){
    public Msg login(@RequestBody Map<String, String> params) {
        String username = params.get(Constant.USERNAME);
        String password = params.get(Constant.PASSWORD);
        User auth = userService.checkUser(username, password);
        if (auth != null && auth.getIsBan() != 1) {

            JSONObject obj = new JSONObject();
            obj.put(Constant.USER_ID, auth.getId());
            obj.put(Constant.USERNAME, auth.getName());
            obj.put(Constant.USER_TYPE, auth.getIsAdministrators());
            SessionUtil.setSession(obj);

            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            JSONObject data = JSONObject.fromObject(auth, jsonConfig);
            data.remove(Constant.PASSWORD);
            /**
             * 开始计时
             */
            timeService.startTimer();
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
        } else if (auth != null) {
            return MsgUtil.makeMsg(MsgUtil.LOGIN_BAN_ERROR, MsgUtil.LOGIN_BAN_MSG, null);
        } else {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    @RequestMapping("/logout")
    public Msg logout() {
        Boolean status = SessionUtil.removeSession();
        /**
         * 停止计时，并将时间信息返回前端
         */
        String timePassed = timeService.endTimer();
        if (status) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, timePassed);
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }

    /**
     * @Description: getSession
     * @Param: null
     * @return: Msg
     */

    @RequestMapping("/checkSession")
    public Msg checkSession() {
        JSONObject auth = SessionUtil.getAuth();

        if (auth == null) {
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        } else {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }
}

