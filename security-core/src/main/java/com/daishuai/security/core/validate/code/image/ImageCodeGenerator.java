package com.daishuai.security.core.validate.code.image;

import com.daishuai.security.core.properties.SecurityProperties;
import com.daishuai.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Description: 默认的图片验证码生成器
 * @Author: daishuai
 * @CreateDate: 2018/9/7 11:33
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

    /**
     * 系统配置
     */
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generator(ServletWebRequest request) {
        //先从request请求中获取，没有就从配置中取
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height" ,securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(this.getRandColor(200, 250));
        g.fillRect(0,0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));

        //生成干扰条纹
        for (int i = 0; i < 155; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        //生成4为随机数
        String sRand = "";
        //验证码长度
        int length = securityProperties.getCode().getImage().getLength();
        for (int i = 0; i < length; i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110) ,20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        //验证码过期时间
        int expireIn = securityProperties.getCode().getImage().getExpireIn();
        return new ImageCode(image, sRand, expireIn);
    }


    /**
     * 生成随机背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int red = fc + random.nextInt(bc - fc);
        int green = fc + random.nextInt(bc - fc);
        int blue = fc + random.nextInt(bc - fc);
        return new Color(red, green, blue);
    }
}
