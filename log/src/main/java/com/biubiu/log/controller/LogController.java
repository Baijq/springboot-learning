package com.biubiu.log.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LogController
 *
 * @author wbbaijq
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/error")
    public String error() {
        int[] i = {1,2,3,4};
        int j = i[5];
        return "测试系统错误日志";
    }
}
