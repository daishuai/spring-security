package com.daishuai.security.app.config;

import com.daishuai.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.daishuai.security.core.authentication.FormAuthenticationConfig;
import com.daishuai.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.daishuai.security.core.authorize.AuthorizeConfigManager;
import com.daishuai.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description: 资源服务器配置：和认证服务器在物理上可以在一起也可以分开
 * @Author: daishuai
 * @CreateDate: 2018/12/26 21:21
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
@EnableResourceServer
public class AppResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer mySocialConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.config(http);

        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(mySocialConfig)
                    .and()
                .apply(openIdAuthenticationSecurityConfig)
                    .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
