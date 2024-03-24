package com.lyy.example.consumer;

import com.lyy.example.common.model.User;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.constant.RpcConstant;
import com.lyy.lyyrpc.proxy.ServiceProxyFactory;
import com.lyy.lyyrpc.utils.ConfigUtils;

/**
 * @author lian
 * @title ConsumerExample
 * @date 2024/3/24 19:12
 * @description 服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 获取代理
        UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        User user = new User();
        user.setName("lian");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}
