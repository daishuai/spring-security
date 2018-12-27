package com.daishuai.security.core.validate.code.sms;

import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.validate.code.ValidateCode;
import com.daishuai.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 短信验证码生成器
 * @Author: daishuai
 * @CreateDate: 2018/12/19 20:12
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
