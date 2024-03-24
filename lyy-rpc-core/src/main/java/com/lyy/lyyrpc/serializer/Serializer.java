package com.lyy.lyyrpc.serializer;

import java.io.IOException;

/**
 * @author lian
 * @title Serializer
 * @date 2024/3/23 20:15
 * @description 序列化接口
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
