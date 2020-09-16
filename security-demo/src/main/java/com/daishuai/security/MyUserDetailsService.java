package com.daishuai.security;

import com.daishuai.security.core.properties.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/31 22:05
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        //验证是否是手机号
        if (username.matches(SecurityConstants.REGEX_MOBILE)) {
            user = findUser(username, SecurityConstants.LOGIN_MOBILE);
        } else {
            user = findUser(username, SecurityConstants.LOGIN_USERNAME);
        }
        return new MyUserDetails(user);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        User user = findUser(userId, SecurityConstants.LOGIN_USERNAME);
        return new SocialUser(userId, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user != null ? user.getRole() : ""));
    }

    private User findUser(String username, String type) {
        log.info("登陆用户：{}", username);
        User user;

        if (SecurityConstants.LOGIN_MOBILE.equals(type)) {
            user = repository.findByMobile(username);
        } else {
            user = repository.findByUsername(username);
        }
        if (user == null){
            throw new UsernameNotFoundException(username + "不存在！");
        }
        return user;
    }
}
