package com.biubiu.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 登录
 *
 * @author baijq
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    public void login(String username, String password) {

    }

}
