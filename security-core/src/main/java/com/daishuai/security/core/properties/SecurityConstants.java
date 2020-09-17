package com.daishuai.security.core.properties;

/**
 * @Description: 安全配置常量
 * @Author: daishuai
 * @CreateDate: 2018/12/19 20:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认的用户名密码登陆请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认的手机验证码登陆请求处理url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE= "/authentication/mobile";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 发送短信验证码或验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 登陆方式为用户名登陆
     */
    String LOGIN_USERNAME = "username";

    /**
     * 登陆方式为手机号登陆
     */
    String LOGIN_MOBILE = "mobile";

    /**
     * 验证手机号的正则表达式
     */
    String REGEX_MOBILE = "^1[3|4|5|8]\\d{9}$";

    /**
     * 默认的openId登陆请求的url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openid";

    /**
     * openid参数名
     */
    String DEFAULT_PARAMETER_NAME_OPENID = "openId";

    /**
     * providerId参数名
     */
    String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";

    /**
     * 获取第三方用户信息的url
     */
    String DEFAULT_SOCIAL_USER_INFO_URL = "/social/user";
}
