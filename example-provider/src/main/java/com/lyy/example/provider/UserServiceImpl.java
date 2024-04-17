package com.lyy.example.provider;

import com.lyy.example.common.model.User;
import com.lyy.example.common.service.UserService;

/**
 * @author lian
 * @title UserServiceImpl
 * @date 2024/3/23 19:21
 * @description 用户服务实现类
 */
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }

    @Override
    public String getAddress(User user) {
        System.out.println("用户地址：" + user.getAddress());
        return "id".concat(user.getAddress());
    }

    @Override
    public String getId(User user) {
        System.out.println("用户id: " + user.getId());
        return "id".concat(user.getId());
    }
}
