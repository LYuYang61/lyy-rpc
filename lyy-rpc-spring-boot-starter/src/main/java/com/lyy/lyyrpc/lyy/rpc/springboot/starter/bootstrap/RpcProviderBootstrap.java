package com.lyy.lyyrpc.lyy.rpc.springboot.starter.bootstrap;

import com.lyy.lyyrpc.RpcApplication;
import com.lyy.lyyrpc.config.RegistryConfig;
import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.lyy.rpc.springboot.starter.annotation.RpcService;
import com.lyy.lyyrpc.model.ServiceMetaInfo;
import com.lyy.lyyrpc.registry.LocalRegistry;
import com.lyy.lyyrpc.registry.Registry;
import com.lyy.lyyrpc.registry.RegistryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author lian
 * @title RpcProviderBootstrap
 * @date 2024/4/9 18:58
 * @description RPC 服务提供者启动
 */
@Slf4j
public class RpcProviderBootstrap implements BeanPostProcessor {

    /**
     * 初始化后后处理
     * Bean 初始化后执行，注册服务
     *
     * @param bean     豆
     * @param beanName Bean 名称
     * @return {@link Object}
     * @throws BeansException Beans 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        RpcService rpcService = beanClass.getAnnotation(RpcService.class);
        if (rpcService != null) {
            // 需要注册服务
            // 1. 获取服务基本信息
            Class<?> interfaceClass = rpcService.interfaceClass();
            // 默认值处理
            if (interfaceClass == void.class) {
                interfaceClass = beanClass.getInterfaces()[0];
            }
            String serviceName = interfaceClass.getName();
            String serviceVersion = rpcService.serviceVersion();
            // 2. 注册服务
            // 本地注册
            LocalRegistry.register(serviceName, beanClass);

            // 全局配置
            final RpcConfig rpcConfig = RpcApplication.getConfig();
            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(serviceVersion);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
