package com.daishuai.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Description: 微信登录配置项
 * @Author: daishuai
 * @CreateDate: 2018/12/23 21:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class WechatProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登陆的url，默认wechat
     */
    private String providerId = "wechat";

}
