package com.lyy.lyyrpc.server.tcp;

import com.lyy.lyyrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lian
 * @title VertxTcpServerTest
 * @date 2024/4/16 16:38
 * @description
 */

@Slf4j
public class VertxTcpServerTest implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                String testMessage = "Hello, server!Hello, server!Hello, server!Hello, server!";
                int messageLength = testMessage.getBytes().length;
                System.out.println("Received message length: " + messageLength);
                if (buffer.getBytes().length < messageLength) {
                    System.out.println("半包 , length = " + buffer.getBytes().length);
                    return;
                }
                if (buffer.getBytes().length > messageLength) {
                    System.out.println("粘包 , length = " + buffer.getBytes().length);
                    return;
                }
                String str = new String(buffer.getBytes(0, messageLength));
                System.out.println("Received message from client: " + str);
                if (testMessage.equals(str)) {
                    System.out.println("Received message is correct");
                }
            });
        });

        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("TCP server started on port " + port);
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });

    }

    public static void main(String[] args) {
        new VertxTcpServerTest().doStart(8889);
    }
}
