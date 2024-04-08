package com.lyy.lyyrpc.fault.tolerant;

import com.lyy.lyyrpc.RpcApplication;
import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author lian
 * @title FailBackTolerantStrategy
 * @date 2024/4/7 19:08
 * @description 降级到其他服务 - 容错策略
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // 可自行扩展，获取降级的服务并调用
        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcApplication.getConfig();
        MockService mockService = MockServiceFactory.getInstance(rpcConfig.getMockService());
        Object mock = mockService.mock();
        return RpcResponse.builder().data(mock).message("ok").build();
    }
}
