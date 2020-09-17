package com.daishuai.security.browser.validate.code.impl;

import com.daishuai.security.core.validate.code.ValidateCode;
import com.daishuai.security.core.validate.code.ValidateCodeRepository;
import com.daishuai.security.core.validate.code.ValidateCodeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 基于session的验证码存取器
 * @Author: daishuai
 * @CreateDate: 2018/12/28 9:32
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入session时的前缀
     */
    private static final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 构造验证码放入session的key
     * @param request
     * @param type
     * @return
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType type) {
        return SESSION_KEY_PREFIX + type.getParamNameOnValidate().toUpperCase();
    }

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        sessionStrategy.setAttribute(request, getSessionKey(request, type), code);
        log.info("验证码存入session，session Key：{}，code：{}", getSessionKey(request,type), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        ValidateCode validateCode = (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request,type));
        log.info("从session中取出验证码，session Key：{}，code：{}", getSessionKey(request, type), validateCode.getCode());
        return validateCode;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        sessionStrategy.removeAttribute(request,getSessionKey(request, type));
        log.info("从session中移除验证码，sessionKey：{}", getSessionKey(request, type));
    }
}
