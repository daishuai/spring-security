package com.daishuai.security.core.social.wechat.connect;

import com.daishuai.security.core.social.wechat.api.Wechat;
import com.daishuai.security.core.social.wechat.api.WechatUserInfo;
import lombok.Data;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Description: 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * @Author: daishuai
 * @CreateDate: 2018/12/23 22:08
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class WechatAdapter implements ApiAdapter<Wechat> {

    private String openId;


    public WechatAdapter(){};

    public WechatAdapter(String openId) {
        this.openId = openId;
    }

    /**
     * 测试微信接口是否通
     * @param api
     * @return
     */
    @Override
    public boolean test(Wechat api) {
        return true;
    }

    @Override
    public void setConnectionValues(Wechat api, ConnectionValues values) {
        WechatUserInfo userInfo = api.getUserInfo(openId);
        values.setProviderUserId(userInfo.getOpenid());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(Wechat api) {
        return null;
    }

    @Override
    public void updateStatus(Wechat api, String message) {

    }
}
