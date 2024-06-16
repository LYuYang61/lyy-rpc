package com.lyy.lyyrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.lyy.lyyrpc.RpcApplication;
import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.constant.RpcConstant;
import com.lyy.lyyrpc.fault.retry.RetryStrategy;
import com.lyy.lyyrpc.fault.retry.RetryStrategyFactory;
import com.lyy.lyyrpc.fault.tolerant.TolerantStrategy;
import com.lyy.lyyrpc.fault.tolerant.TolerantStrategyFactory;
import com.lyy.lyyrpc.loadbalancer.LoadBalancer;
import com.lyy.lyyrpc.loadbalancer.LoadBalancerFactory;
import com.lyy.lyyrpc.model.RpcRequest;
import com.lyy.lyyrpc.model.RpcResponse;
import com.lyy.lyyrpc.model.ServiceMetaInfo;
import com.lyy.lyyrpc.registry.Registry;
import com.lyy.lyyrpc.registry.RegistryFactory;
import com.lyy.lyyrpc.serializer.Serializer;
import com.lyy.lyyrpc.serializer.SerializerFactory;
import com.lyy.lyyrpc.server.tcp.VertxTcpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lian
 * @title ServiceProxy
 * @date 2024/3/23 21:33
 * @description 服务代理（JDK 动态代理）
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 代理方法
     *
     * @param proxy  代理对象
     * @param method 方法
     * @param args   参数
     * @return {@link Object}
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)                     // 服务名
                .methodName(method.getName())                 // 方法名
                .parameterTypes(method.getParameterTypes())   // 参数类型
                .args(args)
                .build();
        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcApplication.getConfig();
        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
        if (CollUtil.isEmpty(serviceMetaInfoList)) {
            throw new RuntimeException("暂无服务地址");
        }
        // 负载均衡
        LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
        // 将调用方法名（请求路径）作为负载均衡参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", rpcRequest.getMethodName());
        ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        // 发送 rpc 请求
        // 使用重试机制
        RpcResponse rpcResponse;
        try {
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
            );
        } catch (Exception e) {
            // 容错机制
            HashMap<String, Object> map = new HashMap<>();
            map.put("serviceList", serviceMetaInfoList);
            // 排查在外的服务
            map.put("errorService", selectedServiceMetaInfo);
            // 传递rpcRequest
            map.put("rpcRequest", rpcRequest);
            TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
            rpcResponse = tolerantStrategy.doTolerant(map, e);

        }

        return rpcResponse.getData();
    }
}
