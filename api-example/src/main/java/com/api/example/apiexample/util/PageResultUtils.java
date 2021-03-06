package com.api.example.apiexample.util;

import com.api.example.apiexample.controller.response.PageResultModel;

/**
 * 分页返回结果工具类
 *
 * @author wbbaijq
 */
public class PageResultUtils {

    /**
     * 成功，携带分页相关数据以及信息
     * @param data     数据
     * @param total    数据总条数
     * @param size     每页条数
     * @param pages    总页数
     * @param current  当前页
     * @param <T>      数据类型
     * @return {@link PageResultModel}
     */
    public static <T> PageResultModel<T> success(T data, long total, int size, long pages, long current) {
        return new PageResultModel<>("success", data, total, size, pages, current);
    }

    public static <T> PageResultModel<T> success(String message, T data) {
        return new PageResultModel<>(message, data);
    }

    public static <T> PageResultModel<T> success(String message, T data, long total, int size, long pages, long current) {
        return new PageResultModel<>(message, data, total, size, pages, current);
    }

    public static <T> PageResultModel<T> error(String message) {
        return new PageResultModel<>(message);
    }
}
