package com.daishuai.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/23 20:39
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DefaultInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public DefaultInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onsessionInvalid(request, response);
    }
}
