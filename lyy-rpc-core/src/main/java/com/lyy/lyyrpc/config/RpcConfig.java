package com.lyy.lyyrpc.config;

import com.lyy.lyyrpc.fault.retry.RetryStrategyKeys;
import com.lyy.lyyrpc.fault.tolerant.MockServiceKeys;
import com.lyy.lyyrpc.fault.tolerant.TolerantStrategyKeys;
import com.lyy.lyyrpc.loadbalancer.LoadBalancerKeys;
import com.lyy.lyyrpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * @author lian
 * @title RpcConfig
 * @date 2024/3/24 18:38
 * @description RPC 全局配置
 */

@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "lyy-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 负载均衡器
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 重试策略
     */
    private String retryStrategy = RetryStrategyKeys.EXPONENTIAL_BACKOFF;

    /**
     * 容错策略
     */
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;

    /**
     * 模拟服务
     */
    private String mockService = MockServiceKeys.DEFAULT;


    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
}
