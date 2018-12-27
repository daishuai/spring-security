package com.daishuai.security.core.social.wechat.connect;

import com.daishuai.security.core.social.wechat.api.Wechat;
import com.daishuai.security.core.social.wechat.api.WechatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Description: 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * @Author: daishuai
 * @CreateDate: 2018/12/24 9:44
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class WechatServiceProvider extends AbstractOAuth2ServiceProvider<Wechat> {

    /**
     * 微信获取授权码的url
     */
    //private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    public WechatServiceProvider(String appId, String appSecret) {
        super(new WechatOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Wechat getApi(String accessToken) {
        return new WechatImpl(accessToken);
    }
}
