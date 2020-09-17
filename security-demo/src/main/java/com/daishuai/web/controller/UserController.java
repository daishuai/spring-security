package com.daishuai.web.controller;

import com.daishuai.security.User;
import com.daishuai.security.UserRepository;
//import com.daishuai.security.app.social.AppSignUpUtils;
import com.daishuai.security.core.dto.ResponseDto;
import com.daishuai.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /*@Autowired
    private AppSignUpUtils appSignUpUtils;*/

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 注册用户
     * @param user
     * @param request
     */
    @PostMapping("/register")
    //@JsonView(User.UserSimpleView.class)
    public ResponseDto register(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String mobile = user.getMobile();
        User result = userRepository.findByUsername(username);

        //用户不存在，则进行注册
        if (result == null) {
            log.info("注册用户：{}", username);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User save = userRepository.save(user);
            //浏览器使用ProviderSignInUtils
            providerSignInUtils.doPostSignUp(username, new ServletWebRequest(request));
            //APP使用AppSignUpUtils
            //appSignUpUtils.doPostSignUp(username, new ServletWebRequest(request));
            return ResponseDto.successResponseDto(save);
        }

        //mobile和数据库mobile不一致
        if (!result.getMobile().equals(mobile)) {
            return ResponseDto.errorResponseDto("401", "手机号与注册的账号不一致");
        }

        String password = user.getPassword();
        //密码不正确
        if (!passwordEncoder.matches(password, result.getPassword())) {
            return ResponseDto.errorResponseDto("401", "密码不正确");
        }

        providerSignInUtils.doPostSignUp(username, new ServletWebRequest(request));
        //appSignUpUtils.doPostSignUp(username, new ServletWebRequest(request));
        return ResponseDto.successResponseDto("绑定成功", "");

    }

    /**
     * 绑定用户
     * @param user
     * @param request
     */
    @PostMapping("/binding")
    public ResponseDto binding(User user, HttpServletRequest request) {
        String username = user.getUsername();
        User result = userRepository.findByUsername(username);
        if (result == null) {
            return ResponseDto.errorResponseDto("1111", "用户不存在，绑定失败");
        }

        if (!passwordEncoder.matches(user.getPassword(), result.getPassword())) {
            return ResponseDto.errorResponseDto("1101", "密码错误，绑定失败");
        }

        providerSignInUtils.doPostSignUp(username, new ServletWebRequest(request));
        return ResponseDto.successResponseDto(null);
    }


    @GetMapping
    //@JsonView(User.UserSimpleView.class)
    public ResponseDto userList(){
        List<User> users = userRepository.findAll();
        return ResponseDto.successResponseDto(users);
    }

    //@RequestMapping("/user/{id:\\d+}") 在URL中使用正则表达式
    @GetMapping(value = "/{username}")
    //@JsonView(User.UserDetailView.class)
    public ResponseDto getUser(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username);
        return ResponseDto.successResponseDto(user);
    }

    @PostMapping("/add")
    public ResponseDto addUser(@Valid @RequestBody User user, BindingResult errors){
        Map<String, Object> result = new HashMap<>();
        if (errors.hasErrors()){
            List<ObjectError> objectErrors = errors.getAllErrors();
            objectErrors.stream().forEach(error -> result.put(((FieldError)error).getField(),error.getDefaultMessage()));

        }
        String password = passwordEncoder.encode(user.getPassword());
        log.info("password:{}", password);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseDto.successResponseDto("");
    }

    /**
     * 获取认证用户信息：方法1
     * @param authentication
     * @param userDetails
     * @return
     */
    @GetMapping("/me")
    public Object getMe(HttpServletRequest request, Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfterLast(header, "bearer ");
        Claims body = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();
        String company = (String) body.get("company");
        log.info("company:{}", company);
        Map<String, Object> map = new HashMap<>();
        map.put("authentication", authentication);
        map.put("userDetails", userDetails);
        return map;
    }

    /**
     * 获取认证用户信息：方法2
     * @return
     */
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
