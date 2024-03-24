package com.lyy.example.consumer;

import com.lyy.lyyrpc.config.RpcConfig;
import com.lyy.lyyrpc.constant.RpcConstant;
import com.lyy.lyyrpc.utils.ConfigUtils;

/**
 * @author lian
 * @title ConsumerExample
 * @date 2024/3/24 19:12
 * @description 服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
