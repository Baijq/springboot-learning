package com.biubiu.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author biubiu
 */
public class DateUtil {

    /**
     * 获取当前时间的字符串
     * @return 2020-04-23 11:10:42
     */
    public static String getCurrentFormatDateStr() {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf2.format(now);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentFormatDateStr(null));
    }

    /**
     * 获取当前时间的字符串，可以指定格式
     * @param format  yyyy-MM-dd HH:mm:ss.SSS
     * @return 根据指定格式返回 2020-04-23 11:11:51.279
     */
    public static String getCurrentFormatDateStr(String format) {
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf2.format(now);
    }
}
