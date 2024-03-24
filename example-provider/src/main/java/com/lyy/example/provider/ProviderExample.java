package com.lyy.example.provider;

import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.RpcApplication;
import com.lyy.lyyrpc.registry.LocalRegistry;
import com.lyy.lyyrpc.server.HttpServer;
import com.lyy.lyyrpc.server.VertxHttpServer;

/**
 * @author lian
 * @title ProviderExample
 * @date 2024/3/24 19:15
 * @description 服务消费者示例
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getConfig().getServerPort());
    }
}
