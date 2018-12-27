package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/1 19:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class BrowserProperties {

    private String loginPage = "/default-login.html";

    private String signUpUrl = "/default-signUp.html";

    private LoginType loginType = LoginType.JSON;

    private String resourceUrl = "";

    /**
     * 记住我有效期
     */
    private int rememberMeSeconds = 60 * 60;

    private SessionProperties session = new SessionProperties();

}
