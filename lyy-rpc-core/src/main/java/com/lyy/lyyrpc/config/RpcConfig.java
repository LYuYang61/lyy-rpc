package com.lyy.lyyrpc.config;

import lombok.Data;

/**
 * @author lian
 * @title RpcConfig
 * @date 2024/3/24 18:38
 * @description RPC 全局配置
 */

@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "lyy-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;
}
