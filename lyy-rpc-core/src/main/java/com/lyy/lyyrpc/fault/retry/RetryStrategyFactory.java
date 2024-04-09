package com.lyy.lyyrpc.fault.retry;

import com.lyy.lyyrpc.spi.SpiLoader;

/**
 * @author lian
 * @title RetryStrategyFactory
 * @date 2024/4/7 18:40
 * @description 重试策略工厂（用于获取重试器对象）
 */
public class RetryStrategyFactory {

    private RetryStrategyFactory() {
        throw new IllegalStateException("Utility class");
    }

    static {
        SpiLoader.load(RetryStrategy.class);
    }


    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }

}
