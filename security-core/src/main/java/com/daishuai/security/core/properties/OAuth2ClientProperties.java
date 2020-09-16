package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 认证服务器注册的第三方应用配置项
 * @Author: daishuai
 * @CreateDate: 2018/12/28 16:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class OAuth2ClientProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;

    /**
     * 第三方应用appSecret
     */
    private String clientSecret;

    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValiditySeconds = 7200;

    /**
     * 针对此应用发出的refresh_token有效期
     */
    private int refreshTokenValiditySeconds = 7300;

    /**
     * 针对此应用发出的权限
     */
    private String scope;

    /**
     * 针对此应用发出的支持的授权模式
     */
    private String grantType;
}
