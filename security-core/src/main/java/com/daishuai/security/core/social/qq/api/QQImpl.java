package com.daishuai.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Description: 获取QQ用户信息接口实现
 * @Author: daishuai
 * @CreateDate: 2018/12/20 20:49
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?accessToken=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        log.info("获取openId接口url:{}", url);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("调用QQ获取openId接口返回结果：{}", result);
        this.openId = StringUtils.substringBetween(result, "openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        log.info("获取QQ用户信息url:{}", url);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("调用QQ获取用户信息接口返回结果：{}", result);
        QQUserInfo userInfo;
        try {
            userInfo =  objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取QQ用户信息失败");
        }
    }
}
