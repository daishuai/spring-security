package com.daishuai.security.core.properties;

import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/12/21 10:47
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class SocialProperties {

    private String filterProcessorsUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WechatProperties wechat = new WechatProperties();
}
