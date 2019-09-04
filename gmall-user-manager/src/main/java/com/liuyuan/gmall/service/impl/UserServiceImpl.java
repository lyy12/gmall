package com.liuyuan.gmall.service.impl;

/**
 * Created by Administrator on 2019/9/5.
 */

import com.liuyuan.gmall.bean.UserInfo;
import com.liuyuan.gmall.mapper.UserMapper;
import com.liuyuan.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public UserInfo getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUser(String id, UserInfo userInfo) {
        userMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userMapper.insertSelective(userInfo);
    }
}

