package com.daishuai.handler;

import com.daishuai.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 异常处理器
 * @Author: daishuai
 * @CreateDate: 2018/8/30 17:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handleUserNotExistException(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
