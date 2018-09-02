package com.daishuai.web.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/30 23:56
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
@Slf4j
public class RequestSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRequest(String request){
        rabbitTemplate.convertAndSend("rabbit.queue", request);
        log.info("发送请求：{}，到队列：rabbit.queue！", request);
    }
}
