package com.daishuai.web.async;

import com.daishuai.web.sender.RequestSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Description: 异步处理Rest服务
 * @Author: daishuai
 * @CreateDate: 2018/8/31 0:03
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private RequestSender requestSender;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    @GetMapping("/async")
    public DeferredResult<String> async(String request){
        log.info("接收到请求：{}", request);
        DeferredResult<String> result = new DeferredResult<>();
        requestSender.sendRequest(request);
        deferredResultHolder.getMap().put(request, result);
        log.info("结束");
        return result;
    }

    @GetMapping
    @RequestMapping("/order")
    public Callable<String> order() {
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(2000);
                log.info("副线程结束");
                return "success";
            }
        };
        log.info("主线程结束");
        return result;
    }
}
