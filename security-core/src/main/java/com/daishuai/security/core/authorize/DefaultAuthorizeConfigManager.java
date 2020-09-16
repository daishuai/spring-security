package com.daishuai.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 默认的授权配置管理器
 * @Author: daishuai
 * @CreateDate: 2018/12/29 16:54
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName = null;

        for (AuthorizeConfigProvider provider : authorizeConfigProviders) {
            boolean currentIsAnyRequestConfig = provider.config(config);

            if (existAnyRequestConfig && currentIsAnyRequestConfig) {
                throw new RuntimeException("重复的anyRequest配置" + existAnyRequestConfigName + "," + provider.getClass().getSimpleName());
            } else if (currentIsAnyRequestConfig) {
                existAnyRequestConfig = true;
                existAnyRequestConfigName = provider.getClass().getSimpleName();
            }
        }
        //如果所有AuthorizeConfigProvider都没有配置anyRequest，自动添加一个默认配置
        if (!existAnyRequestConfig) {
            config.anyRequest().authenticated();
        }
    }
}
