package com.lyy.lyyrpc.registry;

import com.lyy.lyyrpc.spi.SpiLoader;

/**
 * @author lian
 * @title RegistryFactory
 * @date 2024/3/30 19:24
 * @description 注册中心工厂（用于获取注册中心对象）
 */
public class RegistryFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
