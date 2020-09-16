package com.daishuai.security.app;

import com.daishuai.security.app.social.AppSignUpUtils;
import com.daishuai.security.core.dto.SocialUserInfo;
import com.daishuai.security.core.properties.SecurityConstants;
import com.daishuai.security.core.social.SocialController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/28 16:23
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class AppSecurityController extends SocialController {

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 需要注册时跳到这里，返回401和用户信息给前端
     * @param request
     * @return
     */
    @RequestMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());

        return buildSocialUserInfo(connection);
    }
}
