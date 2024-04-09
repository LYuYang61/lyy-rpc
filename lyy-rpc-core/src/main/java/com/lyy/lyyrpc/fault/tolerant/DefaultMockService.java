package com.lyy.lyyrpc.fault.tolerant;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lian
 * @title DefaultMockService
 * @date 2024/4/8 18:28
 * @description 默认模拟服务
 */
@Slf4j
public class DefaultMockService implements MockService {
    @Override
    public Object mock() {
        return null;
    }
}
