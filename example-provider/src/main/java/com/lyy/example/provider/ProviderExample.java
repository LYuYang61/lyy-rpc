package com.lyy.example.provider;

import com.lyy.example.common.service.OrderService;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.bootstrap.ProviderBootstrap;

import com.lyy.lyyrpc.model.ServiceRegisterInfo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


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
        ServiceRegisterInfo serviceRegisterInfo1 = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);


        serviceRegisterInfoList.add(serviceRegisterInfo1);


//        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
//        Arrays.asList(
//                new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class),
//                new ServiceRegisterInfo<>(OrderService.class.getName(), OrderServiceImpl.class)
//        ).forEach(serviceRegisterInfoList::add);
//
//        // 服务提供者初始化
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
