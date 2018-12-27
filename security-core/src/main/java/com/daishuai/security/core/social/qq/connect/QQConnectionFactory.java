package com.daishuai.security.core.social.qq.connect;

import com.daishuai.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/21 10:02
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {


    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
