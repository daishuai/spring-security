package com.daishuai.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 验证码存储策略
 * @Author: daishuai
 * @CreateDate: 2018/12/28 9:21
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code  验证码
     * @param type  验证码类型
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type);

    /**
     * 获取验证码
     * @param request
     * @param type
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType type);

    /**
     * 移除验证码
     * @param request
     * @param type
     */
    void remove(ServletWebRequest request, ValidateCodeType type);
}
