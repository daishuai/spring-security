package com.daishuai.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 验证码生成器接口
 * @Author: daishuai
 * @CreateDate: 2018/9/7 11:32
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface ValidateCodeGenerator {

    /**
     * 生成图形验证码
     * @param request
     * @return
     */
    ValidateCode generator(ServletWebRequest request);
}
