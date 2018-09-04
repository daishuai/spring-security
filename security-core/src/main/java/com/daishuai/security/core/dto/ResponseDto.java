package com.daishuai.security.core.dto;

import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/1 20:16
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ResponseDto {

    private String code;

    private Object body;

    public ResponseDto(String code, Object body){
        this.code = code;
        this.setBody(body);
    }

}
