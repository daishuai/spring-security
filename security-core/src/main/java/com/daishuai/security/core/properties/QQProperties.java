package com.daishuai.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/21 10:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
