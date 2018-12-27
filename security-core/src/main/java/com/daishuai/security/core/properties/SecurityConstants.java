package com.daishuai.security.core.properties;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/19 20:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 当请求需要身份认证时，默认跳转的url
     * @see SecurityController
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认的用户名密码登陆请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认的手机验证码登陆请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE= "/authentication/mobile";

    /**
     * 默认登陆页面
     * @see SecurityController
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/default-login.html";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 发送短信验证码或验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * session失效默认跳转的页面
     */
    public static final String DEFAULT_SESSION_INVALID_PAGE = "/default-session-invalid.html";

    /**
     * 登陆方式为用户名登陆
     */
    public static final String LOGIN_USERNAME = "username";

    /**
     * 登陆方式为手机号登陆
     */
    public static final String LOGIN_MOBILE = "mobile";


    /**
     * 验证手机号的正则表达式
     */
    public static final String REGEX_MOBILE = "^1[3|4|5|8]\\d{9}$";


}
