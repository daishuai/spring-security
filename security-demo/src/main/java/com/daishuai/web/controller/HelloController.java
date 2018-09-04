package com.daishuai.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

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

    /*@RequestMapping("/index")
    public String index(Authentication authentication, @AuthenticationPrincipal UserDetails user){
        log.info("调用接口：/index");
        Map<String, Object> map = new HashMap<>();
        map.put("authentication", authentication);
        map.put("userDetails", user);
        return "index";
    }*/
}
