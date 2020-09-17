package com.daishuai.security.app.config;

import com.daishuai.security.app.jwt.JwtTokenEnhancer;
import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Description: Token存储策略配置
 * @Author: daishuai
 * @CreateDate: 2018/12/29 9:28
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
public class TokenStoreConfig {


    /**
     * 使用redis存储token的配置，只有在imooc.security.oauth2.tokenStore配置为redis时生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "spring-security.oauth2", name = "tokenStore", havingValue = "redis")
    public static class RedisConfig {

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;


        @Bean
        public TokenStore redisTokenStore() {
            return new RedisTokenStore(redisConnectionFactory);
        }

    }


    /**
     * 使用jwt时的配置，默认生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "spring-security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            //设置签名用的密钥
            accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return accessTokenConverter;
        }

        @Bean
        @ConditionalOnMissingBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer(){
            return new JwtTokenEnhancer();
        }
    }
}
