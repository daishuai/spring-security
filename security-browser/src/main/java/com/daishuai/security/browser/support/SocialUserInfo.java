package com.daishuai.security.browser.support;

import lombok.Data;

/**
 * @Description: java类作用描述
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
