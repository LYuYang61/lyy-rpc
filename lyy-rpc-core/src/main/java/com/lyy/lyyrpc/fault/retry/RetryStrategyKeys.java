package com.lyy.lyyrpc.fault.retry;

/**
 * @author lian
 * @title RetryStrategyKeys
 * @date 2024/4/7 18:39
 * @description 重试策略键名常量
 */
public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";

}
