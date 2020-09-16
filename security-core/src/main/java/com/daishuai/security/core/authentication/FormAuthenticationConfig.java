package com.daishuai.security.core.authentication;

import com.daishuai.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @Description: 表单登陆配置
 * @Author: daishuai
 * @CreateDate: 2018/12/31 13:50
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class FormAuthenticationConfig {

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;


    public void config(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler);
    }
}
