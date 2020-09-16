package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 浏览器环境配置项
 * @Author: daishuai
 * @CreateDate: 2018/9/1 19:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class BrowserProperties {

    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();

    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String loginPage = "/default-login.html";

    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpUrl = "/default-signUp.html";

    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;

    /**
     * 登录响应的方式，默认是json
     */
    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    /**
     * 静态资源路径
     */
    private String resourceUrl = "";

    /**
     * '记住我'功能的有效时间，默认1小时
     */
    private int rememberMeSeconds = 60 * 60;

    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
     * 只在 {@link LoginResponseType} loginResponseType 为REDIRECT时生效
     */
    private String signInSuccessUrl;
}
