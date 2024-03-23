package com.lyy.example.common.service;

import com.lyy.example.common.model.User;

/**
 * @author lian
 * @title UserService
 * @date 2024/3/23 19:15
 * @description 用户服务
 */
public interface UserService {

        /**
        * 获取用户
        *
        * @param user
        * @return
        */
        User getUser(User user);


}
