package com.liuyuan.gmall.service;

/**
 * Created by Administrator on 2019/9/5.
 */

import com.liuyuan.gmall.bean.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2019/9/4.
 */
public interface UserService {

    /**
     * 查询所有的用户
     * @return
     */
    List<UserInfo> getAll();

    /**
     * 通过用户的id查询用户信息
     * @param id
     * @return
     */
    UserInfo getUserById(String id);

    /**
     * 通过用户id进行更新
     * @param id
     * @param userInfo
     */
    void updateUser(String id,UserInfo userInfo);

    /**
     * 通过用户id进行更新
     * @param userInfo
     */
    void saveUser(UserInfo userInfo);

}

