package com.lyy.lyyrpc.bootstrap;

import com.lyy.lyyrpc.RpcApplication;

/**
 * @author lian
 * @title ConsumerBootstrap
 * @date 2024/4/9 18:28
 * @description 服务消费者启动类（初始化）
 */
public class ConsumerBootstrap {

    private ConsumerBootstrap() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
