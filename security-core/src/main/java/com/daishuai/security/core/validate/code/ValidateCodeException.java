package com.daishuai.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: 验证码校验异常
 * @Author: daishuai
 * @CreateDate: 2018/9/3 21:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
