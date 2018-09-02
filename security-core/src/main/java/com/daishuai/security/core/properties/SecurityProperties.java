package com.daishuai.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/1 19:58
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
@ConfigurationProperties(prefix = "spring-security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
}
