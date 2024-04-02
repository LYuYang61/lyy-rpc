package com.lyy.lyyrpc.loadbalancer;

import com.lyy.lyyrpc.model.ServiceMetaInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lian
 * @title LoadBalancerTest
 * @date 2024/4/2 18:50
 * @description 负载均衡器测试
 */
public class LoadBalancerTest {

    final LoadBalancer loadBalancer = new RoundRobinLoadBalancer();

    @Test
    public void select() {
        // 请求参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", "apple");
        // 服务列表
        ServiceMetaInfo serviceMetaInfo1 = new ServiceMetaInfo();
        serviceMetaInfo1.setServiceName("myService");
        serviceMetaInfo1.setServiceVersion("1.0");
        serviceMetaInfo1.setServiceHost("localhost");
        serviceMetaInfo1.setServicePort(1234);
        ServiceMetaInfo serviceMetaInfo2 = new ServiceMetaInfo();
        serviceMetaInfo2.setServiceName("myService");
        serviceMetaInfo2.setServiceVersion("1.0");
        serviceMetaInfo2.setServiceHost("xuebao.icu");
        serviceMetaInfo2.setServicePort(80);
        ServiceMetaInfo serviceMetaInfo3 = new ServiceMetaInfo();
        serviceMetaInfo3.setServiceName("myService");
        serviceMetaInfo3.setServiceVersion("1.0");
        serviceMetaInfo3.setServiceHost("aoteman.icu");
        serviceMetaInfo3.setServicePort(800);
        ServiceMetaInfo serviceMetaInfo4 = new ServiceMetaInfo();
        serviceMetaInfo4.setServiceName("myService");
        serviceMetaInfo4.setServiceVersion("1.0");
        serviceMetaInfo4.setServiceHost("lian.icu");
        serviceMetaInfo4.setServicePort(8000);
        ServiceMetaInfo serviceMetaInfo5 = new ServiceMetaInfo();
        serviceMetaInfo5.setServiceName("myService");
        serviceMetaInfo5.setServiceVersion("1.0");
        serviceMetaInfo5.setServiceHost("hfut.icu");
        serviceMetaInfo5.setServicePort(8008);
        List<ServiceMetaInfo> serviceMetaInfoList = Arrays.asList(serviceMetaInfo1, serviceMetaInfo2, serviceMetaInfo3, serviceMetaInfo4, serviceMetaInfo5);
        for (int i = 0; i < 10; i++) {
            ServiceMetaInfo serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            System.out.println(serviceMetaInfo);
            Assert.assertNotNull(serviceMetaInfo);
        }
    }
}
