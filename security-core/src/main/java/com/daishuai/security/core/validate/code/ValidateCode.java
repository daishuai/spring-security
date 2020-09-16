package com.daishuai.security.core.validate.code;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: 验证码信息封装类
 * @Author: daishuai
 * @CreateDate: 2018/12/19 19:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ValidateCode implements Serializable {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(){}

    public ValidateCode(String code, int expireInt) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
