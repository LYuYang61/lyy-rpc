package com.lyy.example.common.model;

import java.io.Serializable;

/**
 * @author lian
 * @title User
 * @date 2024/3/23 19:14
 * @description 用户
 * 对象需要实现序列化接口，为后续网络传输序列化提供支持
 */
public class User implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
