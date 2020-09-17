package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: session管理相关配置项
 * @Author: daishuai
 * @CreateDate: 2018/12/23 20:08
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class SessionProperties {

    /**
     * 同一个用户在系统中的最大session数量，默认1
     */
    private int maximumSessions = 1;

    /**
     * 达到最大session时是否阻止新的登陆，默认为false，不阻止，新的登录会将老的登录失效掉
    */
    private boolean maxSessionsPreventsLogin = false;

    /**
     * session失效时跳转的地址
     */
    private String sessionInvalidUrl = "/default-session-invalid.html";
}
