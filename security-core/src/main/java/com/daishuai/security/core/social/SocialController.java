package com.daishuai.security.core.social;

import com.daishuai.security.core.dto.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/31 14:57
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class SocialController {

    /**
     * 根据Connection信息构建SocialUserInfo
     * @param connection
     * @return
     */
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }
}
