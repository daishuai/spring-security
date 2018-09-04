package com.daishuai.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/3 18:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {

        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
