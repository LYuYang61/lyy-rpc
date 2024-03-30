package com.lyy.lyyrpc.config;

import lombok.Data;

/**
 * @author lian
 * @title RegistryConfig
 * @date 2024/3/26 17:04
 * @description RPC 注册中心配置
 */

@Data
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2381";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;
}
