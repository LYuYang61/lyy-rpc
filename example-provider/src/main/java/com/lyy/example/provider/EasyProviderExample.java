package com.lyy.example.provider;

import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.registry.LocalRegistry;
import com.lyy.lyyrpc.server.HttpServer;
import com.lyy.lyyrpc.server.VertxHttpServer;

/**
 * @author lian
 * @title EasyProviderExample
 * @date 2024/3/23 19:20
 * @description 简易服务提供者示例
 */
public class EasyProviderExample {

        public static void main(String[] args) {
            // 注册服务
            LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

            // 启动 web 服务
            HttpServer httpServer = new VertxHttpServer();
            httpServer.doStart(8080);
        }
}
