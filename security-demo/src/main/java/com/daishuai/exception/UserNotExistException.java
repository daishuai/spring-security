package com.daishuai.exception;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/30 17:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String message){
        super(message);
    }
}
