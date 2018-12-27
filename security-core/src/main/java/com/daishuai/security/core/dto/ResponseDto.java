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

    private String message;

    private Object data;

    public ResponseDto() {

    }

    public ResponseDto(String code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseDto successResponseDto(Object data) {
        return new ResponseDto("0000", "处理成功", data);
    }

    public static ResponseDto successResponseDto(String message, Object data) {
        return new ResponseDto("0000", message, data);
    }

    public static ResponseDto errorResponseDto(String code, String message) {
        return new ResponseDto(code, message, null);
    }

}
