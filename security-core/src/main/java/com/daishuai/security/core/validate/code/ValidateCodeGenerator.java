package com.daishuai.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/7 11:32
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface ValidateCodeGenerator {

    ImageCode generator(ServletWebRequest request);
}
