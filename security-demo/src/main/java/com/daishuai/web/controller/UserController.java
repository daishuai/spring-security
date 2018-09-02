package com.daishuai.web.controller;

import com.daishuai.security.browser.User;
import com.daishuai.security.browser.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/29 20:38
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    public List<User> userList(){

        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    @JsonView(User.UserDetailView.class)
    public User getUser(@PathVariable("username") String username){

        return userRepository.findByUsername(username);
    }

    @PostMapping("/add")
    public Object addUser(@Valid @RequestBody User user, BindingResult errors){
        Map<String, Object> result = new HashMap<>();
        if (errors.hasErrors()){
            List<ObjectError> objectErrors = errors.getAllErrors();
            objectErrors.stream().forEach(error -> result.put(((FieldError)error).getField(),error.getDefaultMessage()));

        }
        String password = passwordEncoder.encode(user.getPassword());
        log.info("password:{}", password);
        user.setPassword(password);
        userRepository.save(user);
        result.put("code", 00);
        result.put("message", "保存成功");
        return result;
    }

    @GetMapping("/user/me")
    public Object getMe(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails){
        Map<String, Object> map = new HashMap<>();
        map.put("authentication", authentication);
        map.put("userDetails", userDetails);
        return map;
    }

}
