package com.biubiu.helloworld.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * ApplicationConfig
 *
 * @author biubiu
 */
@Controller
public class MyProperties1 {

    @Value("${my1.name}")
    private String name;

    @Value("${my1.age}")
    private String age;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
