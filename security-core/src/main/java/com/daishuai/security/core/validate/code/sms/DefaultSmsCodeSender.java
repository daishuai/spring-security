package com.daishuai.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 默认的短信验证码发送器
 * @Author: daishuai
 * @CreateDate: 2018/12/19 19:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        log.info("向手机号：{}，发送短信验证码：{}", mobile ,code);
    }
}
