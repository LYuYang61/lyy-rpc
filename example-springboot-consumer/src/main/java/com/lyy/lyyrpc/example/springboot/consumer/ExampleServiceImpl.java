package com.lyy.lyyrpc.example.springboot.consumer;

import com.lyy.example.common.model.User;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.lyy.rpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * @author lian
 * @title ExampleServiceImpl
 * @date 2024/4/9 19:47
 * @description 示例服务 impl
 */

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("我是雪豹喔");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
