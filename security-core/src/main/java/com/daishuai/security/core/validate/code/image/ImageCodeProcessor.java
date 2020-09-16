package com.daishuai.security.core.validate.code.image;

import com.daishuai.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Description: 图片验证码处理器
 * @Author: daishuai
 * @CreateDate: 2018/12/19 21:17
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图片验证码，将其写到响应中
     * @param request
     * @param imageCode
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(),"JPEG", request.getResponse().getOutputStream());
    }

}
