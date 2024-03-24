package com.lyy.lyyrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author lian
 * @title ServiceProxyFactory
 * @date 2024/3/23 21:38
 * @description 服务代理工厂（用于创建代理对象）
 */
public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
