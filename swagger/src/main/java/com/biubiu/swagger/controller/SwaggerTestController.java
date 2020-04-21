package com.biubiu.swagger.controller;

import com.biubiu.swagger.dto.User;
import com.biubiu.swagger.service.DbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * SwaggerTestController
 *
 * @author baijq
 */
@Api
@RestController
public class SwaggerTestController {

    @Resource
    private DbService dbService;

    @ApiOperation(value = "用户列表接口")
    @GetMapping("/users")
    public List<User> listUser() {
        return dbService.listAllUser();
    }


    @GetMapping("/name")
    @ApiOperation(value = "根据用户编号获取用户姓名", notes = "获取用户名接口: 仅1和2有正确返回")
    public String getUserName(@ApiParam(value = "用户编号", required = true) @RequestParam Integer userNumber) {
        if (userNumber == 1) {
            return "张三丰";
        } else if (userNumber == 2) {
            return "慕容复";
        } else {
            return "未知";
        }
    }
}
