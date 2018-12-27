package com.daishuai.security.core.social.qq.connect;

import com.daishuai.security.core.social.qq.api.QQ;
import com.daishuai.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/21 9:57
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class QQAdapter implements ApiAdapter<QQ> {


    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {

        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do nothing
    }
}