package com.daishuai.security.core.social.wechat.connect;

import lombok.Data;
import org.springframework.social.oauth2.AccessGrant;

/**
 * @Description: 微信的access_token信息。与标准OAuth2协议不同，微信在获取access_token时会同时返回openId,并没有单独的通过accessToke换取openId的服务
 * 所以在这里继承了标准AccessGrant，添加了openId字段，作为对微信access_token信息的封装。
 * @Author: daishuai
 * @CreateDate: 2018/12/23 22:35
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class WechatAccessGrant extends AccessGrant {

    private String openId;



    public WechatAccessGrant() {
        super("");
    }

    public WechatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }
}
