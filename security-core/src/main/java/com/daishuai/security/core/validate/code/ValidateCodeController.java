package com.daishuai.security.core.validate.code;

import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/3 18:57
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = validateCodeGenerator.generator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), ValidateCodeConstant.SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG", response.getOutputStream());
    }


}
