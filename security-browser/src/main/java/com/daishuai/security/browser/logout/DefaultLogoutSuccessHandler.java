package com.daishuai.security.browser.logout;

import com.daishuai.security.core.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 默认的退出成功处理器，如果设置了spring-security.security.browser.signOutUrl，则跳到配置的地址上，
 * 如果没配置，则返回json格式的响应。
 * @Author: daishuai
 * @CreateDate: 2018/12/31 20:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

    private String signOutSuccessUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public DefaultLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (StringUtils.isBlank(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResponseDto.successResponseDto("退出成功", "")));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }
    }
}
