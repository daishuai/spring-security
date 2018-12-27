package com.daishuai.security.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description: Wechat API调用模板，scope为Request的Spring Bean，根据当前用户的accessToken创建
 * @Author: daishuai
 * @CreateDate: 2018/12/23 21:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class WechatImpl extends AbstractOAuth2ApiBinding implements Wechat {

    private ObjectMapper objectMapper = new ObjectMapper();


    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s";


    public WechatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8，所以覆盖原有的方法
     * @return
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WechatUserInfo getUserInfo(String openId) {
        String url = String.format(URL_GET_USER_INFO, openId);
        log.info("获取微信用户信息url:{}", url);
        String response = getRestTemplate().getForObject(url, String.class);
        log.info("调用获取微信用户信息返回响应：{}", response);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }
        WechatUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WechatUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}
