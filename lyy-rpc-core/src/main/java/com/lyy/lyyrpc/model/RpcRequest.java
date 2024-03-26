package com.lyy.lyyrpc.model;

import com.lyy.lyyrpc.constant.RpcConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lian
 * @title RpcRequest
 * @date 2024/3/23 20:25
 * @description RPC 请求
 * 封装调用所需的信息，比如服务名称、方法名称、调用参数的类型列表、参数列表，这些都是 Java 反射机制所需的参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数列表
     */
    private Object[] args;

    /**
     * 服务版本
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;
}
