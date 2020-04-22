package com.biubiu.aopinterceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * AopInterceptorController
 *
 * @author biubiu
 */
@RestController
public class AopInterceptorController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        //这里返回一个空的map，拦截器会做处理
        System.out.println("AopInterceptorController -------  处理请求中...");
        return new HashMap<>();
    }
}
