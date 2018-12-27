package com.daishuai.security.core.social.qq.config;

import com.daishuai.security.core.properties.QQProperties;
import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.social.AbstractSocialAutoConfig;
import com.daishuai.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description: QQ登陆自动配置类
 * @Author: daishuai
 * @CreateDate: 2018/12/21 10:48
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
@ConditionalOnProperty(prefix = "spring-security.social.qq", name = "app-id")
public class QQAutoConfig extends AbstractSocialAutoConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

}
