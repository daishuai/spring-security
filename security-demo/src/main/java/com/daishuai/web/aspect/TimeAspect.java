package com.daishuai.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/30 21:00
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
//@Aspect
@Slf4j
//@Component
public class TimeAspect {

    @Around("execution(* com.daishuai.web.controller..*.* (..))")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("TimeAspect：handleControllerMethod");
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //log.info("TimerAspect：调用接口：{}，耗时：{}！", joinPoint.getSignature().toString(), (end - start));
        //log.info("TimerAspect：调用接口：{}，耗时：{}！", joinPoint.getSignature().toLongString(), (end - start));
        log.info("TimerAspect：调用接口：{}，耗时：{}！", joinPoint.getSignature().toShortString(), (end - start));
        return object;
    }
}
