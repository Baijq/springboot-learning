package com.biubiu.aopinterceptor.interceptorconfig;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器拦截 / 下的所有请求
 *
 * @author biubiu
 */
@Component
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * 有请求过来拦截器拦截，处理一些东西
     */
    private final MyInterceptor myInterceptor;

    private WebConfigurer(MyInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/static/**")//不拦截
                .excludePathPatterns("/templates/**");//不拦截
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }
}
