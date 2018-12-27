package com.daishuai.security.browser.session;

import com.daishuai.security.core.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/23 20:25
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class AbstractSessionStrategy {

    /**
     * 跳转的url
     */
    private String destinationUrl;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void onsessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (createNewSession) {
            request.getSession();
        }
        String sourceUrl = request.getRequestURI();
        log.info("请求的url是：{}", sourceUrl);
        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            log.info("session失效，跳转到{}", destinationUrl);
            redirectStrategy.sendRedirect(request, response, destinationUrl);
        } else {
            String message = "session失效";
            if (isConcurrency()) {
                message = message + "，有可能是并发登陆导致的";
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResponseDto.errorResponseDto("401", message)));
        }
    }

    /**
     * session失效是否是并发导致的
     * @return
     */
    protected boolean isConcurrency() {
        return false;
    }
}
