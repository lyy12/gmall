package com.liuyuan.gmall.controller;

/**
 * Created by Administrator on 2019/9/5.
 */

import com.liuyuan.gmall.bean.UserInfo;
import com.liuyuan.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/9/4.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("all")
    public List<UserInfo> userInfoList(){
        return userService.getAll();
    }
    @RequestMapping("info")
    public UserInfo getUser(String id){
        return userService.getUserById(id);
    }

    @RequestMapping("insert")
    public void saveUser( UserInfo userInfo){
        userService.saveUser(userInfo);
    }

    @RequestMapping("update")
    public void updateUser(String id, UserInfo userInfo){
        userService.saveUser(userInfo);
    }
}

