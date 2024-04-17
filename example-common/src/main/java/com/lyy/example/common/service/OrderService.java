package com.lyy.example.common.service;

import com.lyy.example.common.model.Order;

/**
 * @author lian
 * @title OrderService
 * @date 2024/4/16 22:35
 * @description
 */
public interface OrderService {

    String getOrderId(Order orderId);

    String getOrderName(Order orderName);

}
