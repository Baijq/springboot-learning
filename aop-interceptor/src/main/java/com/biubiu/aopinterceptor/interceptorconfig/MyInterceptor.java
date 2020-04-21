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
        request.setAttribute("startTime", System.currentTimeMillis());
        request.setAttribute("clientIp", HttpHelper.getClientIp(request));
        request.setAttribute("machine", HttpHelper.getMachine());
        //return false会拦截住
        System.out.println("----------------MyInterceptor 处理完毕 ---------------");

        return true;
    }
}
