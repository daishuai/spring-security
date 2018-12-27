package com.daishuai.security.core.validate.code;

import com.daishuai.security.core.properties.SecurityConstants;
import com.daishuai.security.core.validate.code.image.ImageCode;
import com.daishuai.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 校验码控制器
 * @Author: daishuai
 * @CreateDate: 2018/9/3 18:57
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeGenerator imageValidateCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsValidateCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 生成图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code1/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1、生成验证码图片
        ImageCode imageCode = (ImageCode) imageValidateCodeGenerator.generator(new ServletWebRequest(request));
        //2、将验证码存到Session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), ValidateCodeConstant.SESSION_KEY, imageCode);
        //3、将生成的图片写到接口的相应中
        ImageIO.write(imageCode.getImage(),"JPEG", response.getOutputStream());
    }


    @GetMapping("/code1/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        //1、验证码
        ValidateCode smsCode = smsValidateCodeGenerator.generator(new ServletWebRequest(request));
        //2、将验证码存到Session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), ValidateCodeConstant.SESSION_KEY, smsCode);
        //3、从请求中获取手机号（getRequiredStringParameter()方法指请求中必须包含）
        String phone = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        //4、发送验证码
        smsCodeSender.send(phone, smsCode.getCode());
    }
    //------------下面为重构后的代码
    /**
     * 创建验证码，根据验证码类型不同，调用不同 {@link ValidateCodeProcessor} 接口实现
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
    }

}
