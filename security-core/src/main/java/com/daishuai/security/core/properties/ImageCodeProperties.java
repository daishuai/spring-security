package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/4 9:06
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ImageCodeProperties {

    private int width = 67;     //验证码图片宽度

    private int height = 23;    //验证码图片高度

    private int length = 4;     //验证码长度

    private int expireIn = 60;  //验证码过期时间

    private String url;         //需要验证验证码的请求
}
