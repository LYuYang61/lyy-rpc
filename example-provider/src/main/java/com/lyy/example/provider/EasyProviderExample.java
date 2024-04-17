package com.lyy.example.provider;

import com.lyy.example.common.model.Order;
import com.lyy.example.common.service.OrderService;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.bootstrap.ProviderBootstrap;
import com.lyy.lyyrpc.model.ServiceRegisterInfo;
import com.lyy.lyyrpc.registry.LocalRegistry;
import com.lyy.lyyrpc.server.HttpServer;
import com.lyy.lyyrpc.server.VertxHttpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lian
 * @title EasyProviderExample
 * @date 2024/3/23 19:20
 * @description 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {

        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo2 = new ServiceRegisterInfo(OrderService.class.getName(), OrderServiceImpl.class);


        serviceRegisterInfoList.add(serviceRegisterInfo2);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
