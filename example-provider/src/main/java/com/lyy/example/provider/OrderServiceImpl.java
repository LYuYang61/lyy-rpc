package com.lyy.example.provider;

import com.lyy.example.common.model.Order;
import com.lyy.example.common.service.OrderService;

/**
 * @author lian
 * @title OrderServiceImpl
 * @date 2024/4/16 22:36
 * @description
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public String getOrderId(Order order) {
        System.out.println("订单id：" + order.getOrderId());
        return "id".concat(order.getOrderId());
    }

    @Override
    public String getOrderName(Order order) {
        System.out.println("订单名称：" + order.getOrderName());
        return "name".concat(order.getOrderName());
    }
}
