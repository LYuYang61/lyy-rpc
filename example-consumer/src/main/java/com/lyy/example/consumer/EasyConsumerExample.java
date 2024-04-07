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
        ServiceProxyFactory serviceProxyFactory = new ServiceProxyFactory();
        // 动态代理
        UserService userService = serviceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("芝士雪豹 ！！！");
        // 调用
        for (int i = 0; i < 30; i++) {
            User newUser = userService.getUser(user);
            if (newUser != null) {
                System.out.println("我是谁？？？ "+newUser.getName());
            } else {
                System.out.println("user == null");
            }
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}
