package com.daishuai.security.core.validate.code.sms;

import com.daishuai.security.core.properties.SecurityConstants;
import com.daishuai.security.core.validate.code.ValidateCode;
import com.daishuai.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/19 21:22
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
