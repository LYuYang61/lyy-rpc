package com.lyy.lyyrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lian
 * @title ServiceRegisterInfo
 * @date 2024/4/9 18:29
 * @description 服务注册信息类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceRegisterInfo <T> {

        /**
        * 服务名称
        */
        private String serviceName;

        /**
        * 实现类
        */
        private Class<? extends T> implClass;
}
