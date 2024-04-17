package com.lyy.lyyrpc.loadbalancer;

/**
 * @author lian
 * @title LoadBalancerKeys
 * @date 2024/4/2 18:40
 * @description 负载均衡器键名常量
 */
public interface LoadBalancerKeys {

    /**
     * 轮询负载均衡器
     */
    String ROUND_ROBIN = "roundRobin";

    /**
     * 随机负载均衡器
     */
    String RANDOM = "random";

}
