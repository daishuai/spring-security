package com.daishuai.security.browser.session;

import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 并发登录导致session失效时，默认的处理策略
 * @Author: daishuai
 * @CreateDate: 2018/12/23 19:20
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DefaultExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public DefaultExpiredSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
        HttpServletResponse response = eventØ.getResponse();
        onsessionInvalid(eventØ.getRequest(), eventØ.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
