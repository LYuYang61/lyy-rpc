package com.lyy.lyyrpc.fault.tolerant;

import cn.hutool.core.collection.CollUtil;
import com.lyy.lyyrpc.loadbalancer.LoadBalancer;
import com.lyy.lyyrpc.loadbalancer.LoadBalancerFactory;
import com.lyy.lyyrpc.loadbalancer.LoadBalancerKeys;
import com.lyy.lyyrpc.model.RpcRequest;
import com.lyy.lyyrpc.model.RpcResponse;
import com.lyy.lyyrpc.model.ServiceMetaInfo;
import com.lyy.lyyrpc.server.tcp.VertxTcpClient;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author lian
 * @title FailOverTolerantStrategy
 * @date 2024/4/7 19:07
 * @description 转移到其他服务节点 - 容错策略
 */
@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) throws ExecutionException, InterruptedException {
        List<ServiceMetaInfo> serviceMetaInfoList = (List<ServiceMetaInfo>) context.get("serviceList");
        ServiceMetaInfo errorService = (ServiceMetaInfo) context.get("errorService");
        RpcRequest rpcRequest = (RpcRequest) context.get("rpcRequest");
        // 从服务列表中移除错误服务
        serviceMetaInfoList.remove(errorService);
        // 重新调用其他服务
        if (CollUtil.isNotEmpty(serviceMetaInfoList)) {
            // 重新调用其他服务
            // 负载均衡
            // 将调用方法名（请求路径）作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(LoadBalancerKeys.ROUND_ROBIN);
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            return VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
        }
        return null;
    }
}

