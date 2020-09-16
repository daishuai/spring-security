package com.daishuai.security.browser.authentication;

import com.daishuai.security.core.dto.ResponseDto;
import com.daishuai.security.core.properties.LoginResponseType;
import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.util.JsonUtilForJack2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 浏览器环境下登录失败的处理器
 * @Author: daishuai
 * @CreateDate: 2018/9/1 22:29
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
@Slf4j
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("登陆失败");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtilForJack2.beanToJson(new ResponseDto("1111", exception.getMessage(), null)));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
