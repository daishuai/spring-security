package com.daishuai.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/19 19:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("向手机号：{}，发送短信验证码：{}", mobile ,code);
    }
}
