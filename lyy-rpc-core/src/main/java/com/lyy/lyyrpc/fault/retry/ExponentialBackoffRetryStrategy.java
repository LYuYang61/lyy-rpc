package com.lyy.lyyrpc.fault.retry;

import com.github.rholder.retry.*;
import com.lyy.lyyrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lian
 * @title ExponentialBackoffRetryStrategy
 * @date 2024/4/8 18:48
 * @description 指数退避 - 重试策略
 */
@Slf4j
public class ExponentialBackoffRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable 调用
     * @return {@link RpcResponse}
     * @throws ExecutionException 执行异常
     * @throws RetryException     重试异常
     */
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfExceptionOfType(Exception.class)   // 异常重试
                .withWaitStrategy(WaitStrategies.exponentialWait(1000, 30L, TimeUnit.SECONDS))  // 重试间隔为 1 秒，最大重试间隔为 30 秒
                .withStopStrategy(StopStrategies.stopAfterAttempt(4))  // 重试次数为 4 次
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}, 距离第一次重试的延迟 {} 毫秒", attempt.getAttemptNumber()-1, attempt.getAttemptNumber()-1==0?0:attempt.getDelaySinceFirstAttempt());
                    }
                })
                .build();

        return retryer.call(callable);
    }
}
