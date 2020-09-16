package com.daishuai.security.core.validate.code;

import com.daishuai.security.core.properties.SecurityConstants;

/**
 * @Description: 校验码类型
 * @Author: daishuai
 * @CreateDate: 2018/12/19 20:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public enum  ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数时的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
