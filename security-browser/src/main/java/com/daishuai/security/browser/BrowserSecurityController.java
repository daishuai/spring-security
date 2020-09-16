package com.daishuai.security.browser;

import com.daishuai.security.core.dto.SocialUserInfo;
import com.daishuai.security.core.dto.ResponseDto;
import com.daishuai.security.core.properties.SecurityConstants;
import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.social.SocialController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 浏览器环境下与安全相关的服务
 * @Author: daishuai
 * @CreateDate: 2018/9/1 19:36
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
@Slf4j
public class BrowserSecurityController extends SocialController {

    /**
     * HttpSessionRequestCache把当前请求缓存到session里
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份验证时，跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseDto requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取引发跳转的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求为：{}", redirectUrl);
            //引发跳转的url为html请求，就跳转到登陆页面
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")){
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new ResponseDto("401", "访问的服务需要身份认证，请引导用户到登陆页面", null);
    }


    /**
     * 用户第一次社交登录时，会引导用户进行用户注册或绑定，此服务用于在注册或绑定页面获取社交网站用户信息
     * @param request
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));

        return buildSocialUserInfo(connection);
    }

}
