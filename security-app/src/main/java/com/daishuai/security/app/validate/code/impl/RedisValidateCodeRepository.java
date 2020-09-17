package com.daishuai.security.app.validate.code.impl;

import com.daishuai.security.core.validate.code.ValidateCode;
import com.daishuai.security.core.validate.code.ValidateCodeException;
import com.daishuai.security.core.validate.code.ValidateCodeRepository;
import com.daishuai.security.core.validate.code.ValidateCodeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 * @Author: daishuai
 * @CreateDate: 2018/12/28 9:44
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        String key = this.buildKey(request, type);
        log.info("Redis中存入一个新的key:{},value:", key, code);
        //验证码有效期30分钟
        redisTemplate.opsForValue().set(key, code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        String key = this.buildKey(request, type);
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        log.info("从Redis中获取key:{},value:{}", key, value);
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        String key = this.buildKey(request, type);
        log.info("从Redis中删除key:{}", key);
        redisTemplate.delete(key);
    }

    /**
     * 构建验证码在redis中的key
     * @param request
     * @param type
     * @return
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        //获取设备id
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("deviceId为空，请求头中未携带deviceId参数");
        }
        return "code:" + type.getParamNameOnValidate().toLowerCase() + ":" + deviceId;
    }
}
