package com.daishuai.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 校验码处理器，封装不同校验码的处理逻辑
 * @Author: daishuai
 * @CreateDate: 2018/12/19 20:29
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     * @param request
     */
    void validate(ServletWebRequest request);
}
