package com.daishuai.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Description: QQ登录配置项
 * @Author: daishuai
 * @CreateDate: 2018/12/21 10:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class QQProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";
}
