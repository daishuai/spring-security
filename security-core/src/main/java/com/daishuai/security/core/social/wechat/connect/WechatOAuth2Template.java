package com.daishuai.security.core.social.wechat.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 完成微信的OAuth2认证流程的模板类。国内厂商实现的OAuth2每个都不同, spring默认提供的OAuth2Template适应不了，只能针对每个厂商自己微调。
 * @Author: daishuai
 * @CreateDate: 2018/12/23 22:14
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Slf4j
public class WechatOAuth2Template extends OAuth2Template {

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public WechatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }


    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> parameters) {
        StringBuffer accessTokenRequestUrl = new StringBuffer(accessTokenUrl);
        accessTokenRequestUrl.append("?appid=");
        accessTokenRequestUrl.append(clientId);
        accessTokenRequestUrl.append("&secret=");
        accessTokenRequestUrl.append(clientSecret);
        accessTokenRequestUrl.append("&code=");
        accessTokenRequestUrl.append(authorizationCode);
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        accessTokenRequestUrl.append("&redirect_uri=");
        accessTokenRequestUrl.append(redirectUri);
        return getAccessToken(accessTokenRequestUrl);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuffer refreshTokenUrl = new StringBuffer(REFRESH_TOKEN_URL);
        refreshTokenUrl.append("?appid=");
        refreshTokenUrl.append(clientId);
        refreshTokenUrl.append("&grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token=");
        refreshTokenUrl.append(refreshToken);
        return getAccessToken(refreshTokenUrl);
    }

    private AccessGrant getAccessToken(StringBuffer accessTokenRequestUrl) {
        log.info("获取access_token，请求URL:{}", accessTokenRequestUrl.toString());
        String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);
        log.info("获取access_token，响应内容：{}", response);
        Map<String, Object> result = null;

        try {
            result = new ObjectMapper().readValue(response, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
            String errcode = MapUtils.getString(result, "errcode");
            String errmsg = MapUtils.getString(result, "errmsg");
            throw new RuntimeException("获取access_token失败，errcode:" + errcode + ",errmsg:" + errmsg);
        }
        WechatAccessGrant accessToken = new WechatAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in")
        );
        accessToken.setOpenId(MapUtils.getString(result, "openid"));
        return accessToken;
    }


    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     * @param parameters
     * @return
     */
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url.replace("client_id", "appid");
        url = url + "&scope=snsapi_login";
        log.info("获取授权码的url:{}", url);
        return url;
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     * @return
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = converter.getSupportedMediaTypes();
        List<MediaType> types = new ArrayList<>();
        for (MediaType mediaType : mediaTypes) {
            types.add(mediaType);
        }
        types.add(MediaType.TEXT_PLAIN);
        converter.setSupportedMediaTypes(types);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }
}
