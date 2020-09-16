package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 短信验证码配置项
 * @Author: daishuai
 * @CreateDate: 2018/12/19 20:09
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;

    /**
     * 验证码过期时间
     */
    private int expireIn = 60;

    /**
     * 需要验证验证码的请求，多个url用逗号隔开，ant pattern
     */
    private String url = "";
}
