package com.daishuai.security.core.validate.code.sms;

/**
 * @Description: 发送短信验证码
 * @Author: daishuai
 * @CreateDate: 2018/12/19 19:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface SmsCodeSender {

    void send(String phone, String code);
}
