package com.lyy.lyyrpc.fault.retry;

import com.github.rholder.retry.*;
import com.lyy.lyyrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lian
 * @title FixedIntervalRetryStrategy
 * @date 2024/4/7 18:24
 * @description 固定间隔重试 - 重试策略
 */

@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy {

    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfExceptionOfType(Exception.class)   // 异常重试
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))  // 重试间隔为 3 秒
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) // 重试次数为 3 次
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}, 距离第一次重试的延迟 {} 毫秒", attempt.getAttemptNumber() - 1, attempt.getAttemptNumber() - 1 == 0 ? 0 : attempt.getDelaySinceFirstAttempt());
                    }
                })
                .build();
        return retryer.call(callable);
    }
}
