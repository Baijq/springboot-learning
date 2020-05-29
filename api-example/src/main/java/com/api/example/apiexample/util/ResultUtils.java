package com.api.example.apiexample.util;

import com.api.example.apiexample.controller.response.ResultModel;

/**
 * 返回结果工具类
 *
 * @author wbbaijq
 */
public class ResultUtils {

    /**
     *  成功
     * @return {@link ResultModel}
     */
    public static ResultModel<?> success() {
        return new ResultModel<>("success", (Object) null);
    }

    /**
     *  成功，携带数据
     * @param data 数据
     * @param <T>  数据的类型
     * @return {@link ResultModel}
     */
    public static <T> ResultModel <T> success(T data) {
        return new ResultModel<>("success", data);
    }

    /**
     *  错误，携带详细的错误描述信息
     * @param message 详细的错误描述信息
     * @return {@link ResultModel}
     */
    public static <T> ResultModel<T> error(String message) {
        return new ResultModel<>(message);
    }


}
