package com.lyy.example.consumer;

import com.lyy.example.common.model.User;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.proxy.ServiceProxyFactory;

/**
 * @author lian
 * @title EasyConsumerExample
 * @date 2024/3/23 19:26
 * @description 简易服务消费者示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // 静态代理
        // UserService userService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("lian");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
