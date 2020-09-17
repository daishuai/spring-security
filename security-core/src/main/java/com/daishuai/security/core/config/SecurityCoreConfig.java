package com.daishuai.security.core.config;

import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 核心模块配置
 * @Author: daishuai
 * @CreateDate: 2018/9/1 20:08
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
