package com.lyy.lyyrpc.protocol;

/**
 * @author lian
 * @title ProtocolConstant
 * @date 2024/4/1 18:41
 * @description 协议常量
 */
public interface ProtocolConstant {

    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}
