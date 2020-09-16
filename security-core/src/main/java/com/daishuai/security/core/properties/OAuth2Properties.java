package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 第三方应用的配置集合
 * @Author: daishuai
 * @CreateDate: 2018/12/28 16:57
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class OAuth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "daishuai";

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
