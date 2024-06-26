package com.lyy.lyyrpc.fault.tolerant;

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
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
