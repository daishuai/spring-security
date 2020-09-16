package com.daishuai.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Description: SocialAuthenticationFilter后处理器，用于在不同环境下个性化社交登录的配置
 * @Author: daishuai
 * @CreateDate: 2018/12/28 13:58
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter filter);
}
