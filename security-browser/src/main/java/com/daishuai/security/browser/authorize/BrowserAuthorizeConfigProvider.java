package com.daishuai.security.browser.authorize;

import com.daishuai.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Description: 浏览器环境默认的授权配置，对常见的静态资源，如js,css，图片等不验证身份
 * @Author: daishuai
 * @CreateDate: 2018/12/31 20:30
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class BrowserAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(HttpMethod.GET,
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.gif").permitAll();
        return false;
    }
}
