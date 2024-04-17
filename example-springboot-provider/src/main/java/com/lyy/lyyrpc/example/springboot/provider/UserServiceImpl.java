package com.lyy.lyyrpc.example.springboot.provider;

import com.lyy.example.common.model.User;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.lyy.rpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * @author lian
 * @title UserServiceImpl
 * @date 2024/4/9 19:51
 * @description 用户服务实现类
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }


}
