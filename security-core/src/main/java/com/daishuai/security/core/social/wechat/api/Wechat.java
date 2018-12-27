package com.daishuai.security.core.social.wechat.api;

/**
 * @Description: 获取微信用户信息接口
 * @Author: daishuai
 * @CreateDate: 2018/12/23 21:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface Wechat {

    /**
     * 获取微信用户信息
     * @return
     */
    WechatUserInfo getUserInfo(String openId);
}
