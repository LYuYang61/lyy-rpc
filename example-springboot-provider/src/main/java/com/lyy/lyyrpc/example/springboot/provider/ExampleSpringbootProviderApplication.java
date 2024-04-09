package com.lyy.lyyrpc.example.springboot.provider;

import com.lyy.lyyrpc.lyy.rpc.springboot.starter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lian
 * @title ExampleSpringbootProviderApplication
 * @date 2024/4/9 19:51
 * @description 示例springboot提供程序应用程序
 */
@SpringBootApplication
@EnableRpc
public class ExampleSpringbootProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringbootProviderApplication.class, args);
    }

}
