package com.lyy.lyyrpc.bootstrap;

import com.lyy.lyyrpc.RpcApplication;
import com.lyy.lyyrpc.config.RegistryConfig;
import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.model.ServiceMetaInfo;
import com.lyy.lyyrpc.model.ServiceRegisterInfo;
import com.lyy.lyyrpc.registry.LocalRegistry;
import com.lyy.lyyrpc.registry.Registry;
import com.lyy.lyyrpc.registry.RegistryFactory;
import com.lyy.lyyrpc.server.tcp.VertxTcpServer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lian
 * @title ProviderBootstrap
 * @date 2024/4/9 18:29
 * @description 提供程序引导程序
 */
public class ProviderBootstrap {

    private ProviderBootstrap() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 初始化
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册 服务名 -> 实现类
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }

//            // 引入延迟：等待 15 秒钟
//            try {
//                TimeUnit.SECONDS.sleep(120);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            // 启动服务器
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());
        }
    }
}
