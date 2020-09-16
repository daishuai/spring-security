package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 验证码配置
 * @Author: daishuai
 * @CreateDate: 2018/9/4 9:21
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
