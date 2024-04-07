package com.lyy.lyyrpc.fault.tolerent;

import com.lyy.lyyrpc.spi.SpiLoader;

/**
 * @author lian
 * @title TolerantStrategyFactory
 * @date 2024/4/7 19:10
 * @description 容错策略工厂（工厂模式，用于获取容错策略对象）
 */
public class TolerantStrategyFactory {


    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_RETRY_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
