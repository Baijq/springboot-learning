package com.biubiu.apollo.controller;

import com.biubiu.apollo.config.ApolloConfig;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApolloController
 *
 * @author biubiu
 */
@RestController
public class ApolloController {

    @Autowired
    private ApolloConfig apollo;

    /**
     * 测试
     * @return
     */
    @GetMapping("/config")
    public Object getConfigStr() {
        return "【fanhui】：" + getApolloConfig() + (apollo.iosVerCode == null ? "NO-DATA" : apollo.iosVerCode);
    }

    /**
     * 获取配置方式一
     * 调用API方式，最简单，最灵活
     * @return
     */
    private String getApolloConfig() {
        Config config = ConfigService.getConfig("my-namespace");
        String someKey = "myConfigKey";
        String someDefaultValue = "none";
        String value = config.getProperty(someKey, someDefaultValue);
        return value;
    }
}
