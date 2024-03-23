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
}
