package com.lyy.lyyrpc.fault.tolerent;

import com.lyy.lyyrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author lian
 * @title FailSafeTolerantStrategy
 * @date 2024/4/7 19:07
 * @description 静默处理异常 - 容错策略
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常", e);
        return new RpcResponse();
    }
}
