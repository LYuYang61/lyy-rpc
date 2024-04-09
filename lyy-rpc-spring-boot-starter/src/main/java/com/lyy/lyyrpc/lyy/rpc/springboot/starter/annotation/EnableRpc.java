package com.lyy.lyyrpc.lyy.rpc.springboot.starter.annotation;

import com.lyy.lyyrpc.lyy.rpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.lyy.lyyrpc.lyy.rpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.lyy.lyyrpc.lyy.rpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lian
 * @title EnableRpc
 * @date 2024/4/9 18:54
 * @description 启用 RPC 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要服务器
     * 需要启动 server
     *
     * @return boolean
     */
    boolean needServer() default true;
}
