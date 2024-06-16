package com.lyy.example.consumer;

import com.lyy.example.common.model.Order;
import com.lyy.example.common.model.User;
import com.lyy.example.common.service.OrderService;
import com.lyy.example.common.service.UserService;
import com.lyy.lyyrpc.bootstrap.ConsumerBootstrap;
import com.lyy.lyyrpc.proxy.ServiceProxyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lian
 * @title ConsumerExample
 * @date 2024/3/24 19:12
 * @description 服务消费者示例
 */

@Slf4j
public class ConsumerExample {

    public static void main(String[] args) {
        // 服务消费者初始化
        ConsumerBootstrap.init();
        ServiceProxyFactory serviceProxyFactory = new ServiceProxyFactory();
        // 动态代理
        UserService userService = serviceProxyFactory.getProxy(UserService.class);
        //OrderService orderService = serviceProxyFactory.getProxy(OrderService.class);

//        User user = new User();
//        user.setName("芝士雪豹 ！！！");
//        user.setAddress("理塘");
//        user.setId("10086");
//
//        Order order = new Order();
//        order.setOrderId("10001");
//        order.setOrderName("菜狗");


        // 调用
        for (int i = 0; i < 8; i++) {
            User user = new User();
            //user.setName("芝士雪豹：" + i);
            user.setAddress("理塘：" + i);
            //user.setId("10086：" + i);

//            Order order = new Order();
//            order.setOrderId("10001：" + i) ;
//            order.setOrderName("菜狗：" + i);


           // User newUser = userService.getUser(user);
            String address = userService.getAddress(user);
           // String id = userService.getId(user);
            System.out.println("address: " + address);
//            System.out.println("id: "+id);
//            if (newUser != null) {
//                System.out.println("我是谁？？？ "+newUser.getName());
//            } else {
//                System.out.println("user == null");
//            }
//
//            String orderName = orderService.getOrderName(order);
//            String orderId = orderService.getOrderId(order);
//            System.out.println("orderName: "+orderName);
//            System.out.println("orderId: "+orderId);
        }


        long number = userService.getNumber();
        System.out.println(number);
    }
}
