package com.biubiu.aopinterceptor.interceptorconfig;

import com.biubiu.aopinterceptor.util.HttpHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器实现
 *
 * @author biubiu
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("startTime: " + System.currentTimeMillis());
        System.out.println("clientIp: " + HttpHelper.getClientIp(request));
        System.out.println("machine: " + HttpHelper.getMachine());
        System.out.println("浏览器名称：" + HttpHelper.getBrowserName(request));
        System.out.println("浏览器版本：" + HttpHelper.getBrowserVersion(request));
        System.out.println("操作系统名称：" + HttpHelper.getOsName(request));
        //return false会拦截住
        System.out.println("----------------MyInterceptor 处理完毕 ---------------");

        return true;
    }
}
