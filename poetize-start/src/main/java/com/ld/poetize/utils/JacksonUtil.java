package com.ld.poetize.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:02
 **/
public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json
     * @param object
     * @return
     */
    public static String Data2Json(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("自定义方法-->对象转json异常");
        }
    }
}
