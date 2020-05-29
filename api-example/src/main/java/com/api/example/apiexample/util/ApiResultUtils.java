package com.api.example.apiexample.util;

import com.api.example.apiexample.controller.response.ApiResultModel;

/**
 * API接口返回结果工具类
 *
 * @author wbbaijq
 */
public class ApiResultUtils {

    /**
     * 成功，携带返回码和描述信息
     * @return {@link ApiResultModel}
     */
    public static ApiResultModel<Integer, ?> success() {
        return new ApiResultModel<>(0, "success", (Object)null);
    }

    /**
     * 成功，携带返回码、描述信息和数据
     * @param data 数据
     * @param <T>  数据的类型
     * @return {@link ApiResultModel}
     */
    public static <T> ApiResultModel<Integer, T> success(T data) {
        return new ApiResultModel<>(0, "success", data);
    }

    /**
     * 出错，携带错误吗和详细描述信息
     * @param code 返回码
     * @param message 相信描述信息
     * @return {@link ApiResultModel}
     */
    public static ApiResultModel<Integer, ?> error(int code, String message) {
        return new ApiResultModel<>(code, message);
    }

}
