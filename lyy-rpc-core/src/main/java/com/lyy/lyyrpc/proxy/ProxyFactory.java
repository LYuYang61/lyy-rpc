package com.lyy.lyyrpc.proxy;

/**
 * @author lian
 * @title ProxyFactory
 * @date 2024/4/9 19:18
 * @description
 */
public interface ProxyFactory {

    <T> T getProxy(Class<T> serviceClass);
}
