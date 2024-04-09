package com.lyy.example.consumer;

import com.lyy.example.common.model.User;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.bootstrap.ConsumerBootstrap;
import com.lyy.lyyrpc.proxy.ServiceProxyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lian
 * @title ConsumerExample
 * @date 2024/3/24 19:12
 * @description 服务消费者示例
 */

@Slf4j
public class ConsumerExample {

    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();
        ServiceProxyFactory serviceProxyFactory = new ServiceProxyFactory();
        // 动态代理
        UserService userService = serviceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("芝士雪豹 ！！！");
        // 调用
        for (int i = 0; i < 10; i++) {
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
