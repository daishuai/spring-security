package com.daishuai.security.core.social.wechat.config;

import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.properties.WechatProperties;
import com.daishuai.security.core.social.AbstractSocialAutoConfig;
import com.daishuai.security.core.social.DefaultConnectView;
import com.daishuai.security.core.social.wechat.connect.WechatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/24 10:07
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
@ConditionalOnProperty(prefix = "spring-security.social.wechat", name = "app-id")
public class WechatAutoConfig extends AbstractSocialAutoConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        WechatProperties wechat = securityProperties.getSocial().getWechat();

        return new WechatConnectionFactory(wechat.getProviderId(),wechat.getAppId(),wechat.getAppSecret());
    }

    @Bean({"connect/wechatConnect", "connect/wechatConnected"})
    @ConditionalOnMissingBean(name = "wechatConnectedView")
    public View wechatConnectView() {
        return new DefaultConnectView();
    }
}
