package com.daishuai.handler;

import com.daishuai.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handleUserNotExistException(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.info("全局异常处理器");
        e.printStackTrace();
    }
}
