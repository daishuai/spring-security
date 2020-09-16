package com.daishuai.security.core.dto;

import lombok.Data;

/**
 * @Description: 社交用户信息
 * @Author: daishuai
 * @CreateDate: 2018/12/21 15:17
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    /**
     * 头像
     */
    private String headimg;
}
