package com.lyy.example.common.model;

import java.io.Serializable;

/**
 * @author lian
 * @title Order
 * @date 2024/4/16 22:34
 * @description
 */
public class Order implements Serializable {

    private String orderId;

    private String orderName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

}
