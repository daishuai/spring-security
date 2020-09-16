package com.daishuai.security.browser.authentication;

import com.daishuai.security.core.properties.LoginResponseType;
import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.util.JsonUtilForJack2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 浏览器环境下登录失败的处理器
 * @Author: daishuai
 * @CreateDate: 2018/9/1 22:07
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
@Slf4j
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        log.info("登陆成功");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())){
            log.info("登陆方式：JSON");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtilForJack2.beanToJson(authentication));
        } else {
            log.info("登陆方式：{}", securityProperties.getBrowser().getLoginResponseType());
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
