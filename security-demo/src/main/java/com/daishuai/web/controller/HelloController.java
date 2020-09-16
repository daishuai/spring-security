package com.daishuai.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/28 21:43
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Controller
@Slf4j
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

}
