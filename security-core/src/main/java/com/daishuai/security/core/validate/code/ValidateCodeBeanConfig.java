package com.daishuai.security.core.validate.code;

import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.validate.code.image.ImageCodeGenerator;
import com.daishuai.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.daishuai.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 验证码相关的扩展点配置。配置在这里的bean，
 * 业务系统都可以通过声明同类型或同名的bean来覆盖安全模块默认的配置。
 * @Author: daishuai
 * @CreateDate: 2018/9/7 15:32
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 图片验证码图片生成器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 短信验证码发送器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        SmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
        return smsCodeSender;
    }
}
