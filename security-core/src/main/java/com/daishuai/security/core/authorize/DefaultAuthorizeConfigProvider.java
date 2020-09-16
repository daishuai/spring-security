package com.daishuai.security.core.authorize;

import com.daishuai.security.core.properties.SecurityConstants;
import com.daishuai.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Description: 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @Author: daishuai
 * @CreateDate: 2018/12/29 16:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getLoginPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl()
                ).permitAll();
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if (StringUtils.isNotBlank(signOutUrl)) {
            config.antMatchers(signOutUrl).permitAll();
        }
        return false;
    }
}
