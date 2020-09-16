package com.daishuai.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @Description: 默认的SocialUserDetailsService实现
 * 不做任何处理，只在控制台打印一句日志，然后抛出异常，提醒业务系统自己配置SocialUserDetailsService。
 * @Author: daishuai
 * @CreateDate: 2018/12/31 13:41
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class DefaultSocialUserDetailsService implements SocialUserDetailsService {


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("请配置SocialUserDetailsService接口的实现。");
        throw new UsernameNotFoundException(userId);
    }
}
