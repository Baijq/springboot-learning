package com.biubiu.base.regex;

import java.text.MessageFormat;

/**
 * Template
 *
 * @author biubiu
 */
public class Template {

    public static void main(String[] args) {

        String msg = "oh, {0} is ''a'' pig!";
        String[] array = new String[]{"小黄"};
        System.out.println(MessageFormat.format(msg, array));

        System.out.println(String.format("http://www.{0}.com", "baidu"));//错误的
        System.out.println(String.format("http://www.%s.com", "baidu"));//%s替换字符串
        System.out.println(String.format("http://www.%S.com", "baidu"));//%S替换字符串(大写)
        System.out.println(MessageFormat.format("http://www.{0}.com", "baidu"));
    }

}
