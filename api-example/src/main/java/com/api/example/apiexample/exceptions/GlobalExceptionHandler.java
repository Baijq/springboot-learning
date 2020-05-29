package com.api.example.apiexample.exceptions;

import com.api.example.apiexample.controller.response.ApiResultModel;
import com.api.example.apiexample.util.ApiResultUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author wbbaijq
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * HTTP 请求方式不支持异常
     * HttpRequestMethodNotSupportedException
     * @return {@link ApiResultModel}
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ApiResultModel<?, ?> httpRequestMethodNotSupportException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return ApiResultUtils.error(1101, "请求方式不支持异常");
    }

    /**
     * NotBlank，NotNull注解的message信息
     * e.getBindingResult().getFieldError().getDefaultMessage() 就是获取默认的异常信息
     * @return {@link ApiResultModel}
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResultModel<?, ?> bindException(BindException e, HttpServletRequest request) {
        return ApiResultUtils.error(1102, e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
