package com.daishuai.security.core.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/9/1 20:23
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class JsonUtilForJack2 {

    private static ObjectMapper objectMapper = null;

    public static ObjectMapper newInstance(){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();

            //序列化的时候序列对象的所有属性
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

            //反序列化的时候如果多了其他属性,不抛出异常
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //如果是空对象的时候,不抛异常
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
            //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }
        return objectMapper;
    }


    /**
     * 将对象转换成JSON字符串
     * @param object
     * @return
     */
    public static String beanToJson(Object object){
        try {
            return newInstance().writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T jsonToBean(String json, Class<T> cls){
        try {
            return newInstance().readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
