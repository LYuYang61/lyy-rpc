package com.lyy.lyyrpc.server;

/**
 * @author lian
 * @title HttpServer
 * @date 2024/3/23 19:34
 * @description HTTP 服务器接口
 */
public interface HttpServer {

        /**
        * 启动服务器
        *
        * @param port 端口
        */
        void doStart(int port);

}
