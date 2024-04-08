package com.lyy.lyyrpc.fault.tolerant;

import com.lyy.lyyrpc.spi.SpiLoader;

/**
 * @author lian
 * @title MockServiceFactory
 * @date 2024/4/8 18:29
 * @description 模拟服务工厂
 */
public class MockServiceFactory {
    static {
        SpiLoader.load(MockService.class);
    }


    /**
     * 获取实例
     *
     * @param key 钥匙
     * @return {@link MockService}
     */
    public static MockService getInstance(String key) {
        return SpiLoader.getInstance(MockService.class, key);
    }
}
