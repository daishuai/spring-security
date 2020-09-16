package com.daishuai.security.app.authentication;

import com.daishuai.security.core.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: APP环境下认证成功处理器
 * @Author: daishuai
 * @CreateDate: 2018/12/26 16:19
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
@Component
public class AppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * SavedRequestAwareAuthenticationSuccessHandler：
     * Spring默认的登陆成功处理器，实现了AuthenticationSuccessHandler接口，
     * 适配登陆后 重定向和返回json两种用这个实现
     */


    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    /**
     * SpringMVC启动会自动注册一个ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登陆成功");
        //获取头里的Authentication信息
        String header = request.getHeader("Authorization");

        //没有client信息
        if (header == null || !header.startsWith("Basic")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        /**
         * 构造OAuth2Request 第一步：从请求头中获取clientId
         */

        //base64解码获取clientId，clientSecret
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];

        /**
         * 构造OAuth2AccessToken  第二步：根据clientId 获取ClientDetails对象
         */
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在：" + clientId);
        }

        if (!StringUtils.equals(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配，clientId：" + clientId);
        }

        /**
         * 构造OAuth2AccessToken  第三步：new TokenRequest()
         * 第一个参数是Map，放的是每个授权模式特有的参数，Spring Security会根据这些参数构建Authentication
         * 我们这里已经获取到Authentication，所以传个空Map就可以
         */
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

        /**
         * 构造OAuth2AccessToken  第四步：创建OAuth2Request
         */
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        /**
         * 构造OAuth2AccessToken  第五步：创建OAuth2Authentication
         */
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        /**
         * 构造OAuth2AccessToken  第六步：通过AuthorizationServerTokenServices创建OAuth2AccessToken
         */
        OAuth2AccessToken auth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseDto.successResponseDto(auth2AccessToken)));
    }


    /**
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     * Base64
     */
    /**
     * base64解码请求头 Basic
     * Decodes the header into a username and password.
     * @param header
     * @param request
     * @return
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        //返回的数组是    [用户名（就是client_id）,密码（就是clientSecret）]
        /**
         * security.oauth2.client.client-id:
         * security.oauth2.client.client-secret:
         */
        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }
}
