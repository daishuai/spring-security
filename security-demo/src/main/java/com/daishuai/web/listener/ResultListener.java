package com.daishuai.web.listener;

import com.daishuai.web.async.DeferredResultHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/30 23:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
@Slf4j
public class ResultListener {

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    //@RabbitListener(queues = "rabbit.result")
    public void receiveResult(String result){
        log.info("收到返回结果：{}", result);
        deferredResultHolder.getMap().get(result).setResult("成功完成异步处理Rest服务！");
    }
}
