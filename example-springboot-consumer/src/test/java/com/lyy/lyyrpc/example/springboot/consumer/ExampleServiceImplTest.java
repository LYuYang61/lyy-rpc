package com.lyy.lyyrpc.example.springboot.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author lian
 * @title ExampleServiceImplTest
 * @date 2024/4/9 19:54
 * @description
 */
@SpringBootTest
class ExampleServiceImplTest {

    @Resource
    private ExampleServiceImpl exampleService;

    @Test
    void test1() {
        exampleService.test();
    }
}