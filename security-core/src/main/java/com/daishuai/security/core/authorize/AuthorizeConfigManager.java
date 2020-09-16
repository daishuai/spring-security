package com.daishuai.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @Description: 授权信息管理器
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * @Author: daishuai
 * @CreateDate: 2018/12/29 16:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface AuthorizeConfigManager {

    /**
     * 配置权限认证
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
