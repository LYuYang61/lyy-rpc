package com.lyy.lyyrpc.fault.tolerent;

import com.lyy.lyyrpc.model.RpcResponse;

import java.util.Map;

/**
 * @author lian
 * @title FailFastTolerantStrategy
 * @date 2024/4/7 19:08
 * @description
 */
public class FailFastTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}
