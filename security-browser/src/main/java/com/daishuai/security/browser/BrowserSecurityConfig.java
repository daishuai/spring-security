package com.daishuai.security.browser;

import com.daishuai.security.core.authentication.AbstractChannelSecurityConfig;
import com.daishuai.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.daishuai.security.core.properties.SecurityConstants;
import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.validate.code.ValidateCodeSecurityConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 浏览器安全配置类
 * @Author: daishuai
 * @CreateDate: 2018/8/31 20:49
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mySocialConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //是否启动的时候创建表
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] resourceUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getBrowser().getResourceUrl(), ",");
        List<String> urls = new ArrayList<>();
        Collections.addAll(urls,resourceUrls);
        urls.add(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL);
        urls.add(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE);
        urls.add(securityProperties.getBrowser().getLoginPage());
        urls.add(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*");
        urls.add(securityProperties.getBrowser().getSignUpUrl());
        urls.add(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
        urls.add("/user/register");
        urls.add("/user/binding");
        urls.add("/user/add");

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                //最大的session数量
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                //当达到最大session数量时，阻止后面的登陆
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(urls.toArray(new String[urls.size()]))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}
