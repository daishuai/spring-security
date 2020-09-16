package com.daishuai.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description: 继承默认的社交登录配置，加入自定义的后处理逻辑
 * @Author: daishuai
 * @CreateDate: 2018/12/21 11:27
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessorsUrl;

    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public MySpringSocialConfigurer(String filterProcessorsUrl) {
        this.filterProcessorsUrl = filterProcessorsUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessorsUrl);
        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        return (T) filter;
    }

    public void setFilterProcessorsUrl(String filterProcessorsUrl) {
        this.filterProcessorsUrl = filterProcessorsUrl;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }

    public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
        return socialAuthenticationFilterPostProcessor;
    }

    public String getFilterProcessorsUrl() {
        return filterProcessorsUrl;
    }
}
