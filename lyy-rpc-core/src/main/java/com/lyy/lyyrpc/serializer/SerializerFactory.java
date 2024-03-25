package com.lyy.lyyrpc.serializer;

import com.lyy.lyyrpc.spi.SpiLoader;

/**
 * @author lian
 * @title SerializerFactory
 * @date 2024/3/25 21:16
 * @description 序列化器工厂（工厂模式，用于获取序列化器对象）
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}
