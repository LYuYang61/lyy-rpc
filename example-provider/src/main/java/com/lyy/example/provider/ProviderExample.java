package com.lyy.example.provider;

import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.RpcApplication;
import com.lyy.lyyrpc.bootstrap.ProviderBootstrap;
import com.lyy.lyyrpc.config.RegistryConfig;
import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.model.ServiceMetaInfo;
import com.lyy.lyyrpc.model.ServiceRegisterInfo;
import com.lyy.lyyrpc.registry.LocalRegistry;
import com.lyy.lyyrpc.registry.Registry;
import com.lyy.lyyrpc.registry.RegistryFactory;
import com.lyy.lyyrpc.server.HttpServer;
import com.lyy.lyyrpc.server.VertxHttpServer;
import com.lyy.lyyrpc.server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lian
 * @title ProviderExample
 * @date 2024/3/24 19:15
 * @description 服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {

        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);

//        // RPC 框架初始化
//        RpcApplication.init();
//
//        // 注册服务
//        String serviceName = UserService.class.getName();
//        LocalRegistry.register(serviceName, UserServiceImpl.class);
//
//        // 注册服务到注册中心
//        RpcConfig rpcConfig = RpcApplication.getConfig();
//        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
//        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
//        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
//        serviceMetaInfo.setServiceName(serviceName);
//        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
//        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
//
//        try {
//            registry.register(serviceMetaInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        // 启动 TCP 服务
//        VertxTcpServer vertxTcpServer = new VertxTcpServer();
//        vertxTcpServer.doStart(RpcApplication.getConfig().getServerPort());


        // 启动 web 服务
//        HttpServer httpServer = new VertxHttpServer();
//        httpServer.doStart(RpcApplication.getConfig().getServerPort());
    }
}
