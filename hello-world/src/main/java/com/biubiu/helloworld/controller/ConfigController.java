package com.biubiu.helloworld.controller;

import com.biubiu.helloworld.config.MyProperties1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigController
 *
 * @author biubiu
 */
@RestController
public class ConfigController {

    private final MyProperties1 myProperties1;
    private ConfigController(MyProperties1 myProperties1) {
        this.myProperties1 = myProperties1;
    }


    @GetMapping("/config")
    public String getConfig(){
        System.out.println(myProperties1);

        return "myProperties1的Age和Name" + myProperties1.getAge() + myProperties1.getName();
    }
}
