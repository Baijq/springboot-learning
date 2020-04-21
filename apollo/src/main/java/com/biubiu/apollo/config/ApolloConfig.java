package com.biubiu.apollo.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ApolloConfig
 *
 * @author biubiu
 */
@Configuration
@EnableApolloConfig(value = "namespace") //AppVersion指定了namespace
public class ApolloConfig {
    //可以直接通过这种方式获取配置值
    @Value("${IOSVerCode}")
    public String iosVerCode;

}
