package com.lyy.lyyrpc.fault.retry;

import com.lyy.lyyrpc.model.RpcResponse;
import org.junit.Test;

/**
 * @author lian
 * @title RetryStrategyTest
 * @date 2024/4/7 18:34
 * @description 重试策略测试
 */
public class RetryStrategyTest {

    RetryStrategy retryStrategy = new NoRetryStrategy();
    RetryStrategy fixedIntervalRetryStrategy = new FixedIntervalRetryStrategy();
    RetryStrategy exponentialBackoffRetryStrategy = new ExponentialBackoffRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
    @Test
    public void dofixedIntervalRetry() {
        try {
            RpcResponse rpcResponse = fixedIntervalRetryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
    @Test
    public void exponentialBackoffRetry() {
        try {
            RpcResponse rpcResponse = exponentialBackoffRetryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}
