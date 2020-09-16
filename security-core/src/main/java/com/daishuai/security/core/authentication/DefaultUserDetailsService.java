package com.daishuai.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Description: 默认的 UserDetailsService 实现
 * 不做任何处理，只在控制台打印一句日志，然后抛出异常，提醒业务系统自己配置 UserDetailsService。
 * @Author: daishuai
 * @CreateDate: 2018/12/31 13:39
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class DefaultUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("请配置UserDetailsService接口的实现");
        throw new UsernameNotFoundException(username);
    }
}
