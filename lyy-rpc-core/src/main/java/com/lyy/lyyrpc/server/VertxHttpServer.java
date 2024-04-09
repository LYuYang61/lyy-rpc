package com.lyy.lyyrpc.server;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @title server
 * @date 2024/3/23 19:35
 * @author lian
 * @description Vertx HTTP 服务器
 */
@Slf4j
public class VertxHttpServer implements HttpServer {

    /**
     * 启动服务器
     *
     * @param port 端口
     */
    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());
//        server.requestHandler(request -> {
//            // 处理 HTTP 请求
//            System.out.println("Received request: " + request.method() + " " + request.uri());
//
//            // 发送 HTTP 响应
//            request.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("Hello from Vert.x HTTP server!");
//
//        });

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });

    }
}
