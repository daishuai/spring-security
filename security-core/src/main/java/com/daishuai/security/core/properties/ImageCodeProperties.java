package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: 图片验证码配置项
 * @Author: daishuai
 * @CreateDate: 2018/9/4 9:06
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        setLength(4);
    }

    /**
     * 验证码图片宽度
     */
    private int width = 67;

    /**
     * 验证码图片高度
     */
    private int height = 23;

}
