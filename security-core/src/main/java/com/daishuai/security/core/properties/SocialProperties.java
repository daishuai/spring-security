package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 社交登录配置项
 * @Author: daishuai
 * @CreateDate: 2018/12/21 10:47
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class SocialProperties {

    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessorsUrl = "/auth";

    /**
     * 默认支持QQ登陆
     */
    private QQProperties qq = new QQProperties();

    /**
     * 默认支持微信登陆
     */
    private WechatProperties wechat = new WechatProperties();
}
