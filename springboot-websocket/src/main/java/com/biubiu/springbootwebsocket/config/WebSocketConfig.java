package com.biubiu.springbootwebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocketConfig
 *
 * @author wbbaijq
 */
@Configuration
public class WebSocketConfig {

    /** 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
