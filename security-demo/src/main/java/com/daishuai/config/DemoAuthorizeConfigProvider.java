package com.daishuai.config;

import com.daishuai.security.core.authorize.AuthorizeConfigProvider;
import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Description: 用户自定义权限配置
 * @Author: daishuai
 * @CreateDate: 2018/12/29 17:01
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/register","/social/signUp", "/user/binding").permitAll();

        return false;
    }
}
