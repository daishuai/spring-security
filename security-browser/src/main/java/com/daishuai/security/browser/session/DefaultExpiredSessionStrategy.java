package com.daishuai.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/23 19:20
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DefaultExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public DefaultExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
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
