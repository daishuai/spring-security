package com.daishuai.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/21 11:27
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessorsUrl;

    public MySpringSocialConfigurer(String filterProcessorsUrl) {
        this.filterProcessorsUrl = filterProcessorsUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessorsUrl);
        return (T) filter;
    }
}
