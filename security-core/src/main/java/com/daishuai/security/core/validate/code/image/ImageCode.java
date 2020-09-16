package com.daishuai.security.core.validate.code.image;

import com.daishuai.security.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description: 图形验证码
 * @Author: daishuai
 * @CreateDate: 2018/12/19 21:27
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(){}

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }
}
