package com.biubiu.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * JsonUtil
 *
 * @author biubiu
 */
public class JsonUtil {

    private static String getJson(Object obj) {
        return getJson(obj, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object obj, String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();
        //1.不返回时间戳，关闭返回时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //2.指定日期时间格式
        mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
