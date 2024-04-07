package com.lyy.lyyrpc.fault.retry;

import com.lyy.lyyrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author lian
 * @title RetryStrategy
 * @date 2024/4/7 18:20
 * @description 重试策略
 */
public interface RetryStrategy {
    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
