package com.lyy.lyyrpc.loadbalancer;

import com.lyy.lyyrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lian
 * @title RoundRobinLoadBalancer
 * @date 2024/4/2 18:27
 * @description 轮询负载均衡器
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    /**
     * 当前轮询的下标
     */
    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }
        // 只有一个服务，无需轮询
        int size = serviceMetaInfoList.size();
        if (size == 1) {
            return serviceMetaInfoList.get(0);
        }

        // 取模算法轮询
        int currentIndex = index.getAndIncrement() % size;
        return serviceMetaInfoList.get(currentIndex);
    }
}
