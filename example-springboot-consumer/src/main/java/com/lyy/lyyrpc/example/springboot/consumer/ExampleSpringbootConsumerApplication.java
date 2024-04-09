package com.lyy.lyyrpc.example.springboot.consumer;

import com.lyy.lyyrpc.lyy.rpc.springboot.starter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lian
 * @title ExampleSpringbootConsumerApplication
 * @date 2024/4/9 19:48
 * @description 示例 springboot consumer 应用程序
 */
@SpringBootApplication
@EnableRpc(needServer = false)
public class ExampleSpringbootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringbootConsumerApplication.class, args);
    }

}
