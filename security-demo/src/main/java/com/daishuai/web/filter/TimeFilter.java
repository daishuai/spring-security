package com.daishuai.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 过滤器
 * @Author: daishuai
 * @CreateDate: 2018/8/30 19:05
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        log.info("TimerFilter：调用接口：{}，耗时：{}毫秒！", ((HttpServletRequest)request).getRequestURI(), (end - start));// /add
        //log.info("调用接口：{}，耗时：{}毫秒！", ((HttpServletRequest)request).getRequestURL(), (end - start));// http://localhost:7878/add，
        //log.info("调用接口：{}，耗时：{}毫秒！", ((HttpServletRequest)request).getContextPath(), (end - start));//
        //log.info("调用接口：{}，耗时：{}毫秒！", ((HttpServletRequest)request).getServletPath(), (end - start));// /add
    }

    @Override
    public void destroy() {
        log.info("TimeFilter destroy");
    }
}
