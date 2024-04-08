package com.lyy.lyyrpc.fault.tolerant;

import com.lyy.lyyrpc.model.RpcResponse;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author lian
 * @title TolerantStrategy
 * @date 2024/4/7 19:06
 * @description 容错策略
 */
public interface TolerantStrategy {
    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e) throws ExecutionException, InterruptedException;
}
