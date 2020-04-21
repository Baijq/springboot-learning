package com.biubiu.mybatismapper.controller;

import com.biubiu.mybatismapper.entity.User;
import com.biubiu.mybatismapper.service.UserService;
import com.biubiu.mybatismapper.util.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserController
 *
 * @author biubiu
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public RestResponse listUsers() {
        return RestResponse.success().setData(userService.queryAllUser());
    }

    @GetMapping("/users/{id}")
    public RestResponse listUserById(@PathVariable("id") Integer id) {
        return RestResponse.success().setData(userService.queryUserById(id));
    }

    @PostMapping("/updateUsers")
    public RestResponse updateUserById(@RequestBody User user) {
        userService.updateUserByid(user);
        return RestResponse.success();
    }

    @PostMapping("/addUser")
    public RestResponse addUser(@RequestBody User user) {
        userService.addUser(user);
        return RestResponse.success();
    }

    @GetMapping("/deleteUserByids")
    public RestResponse deleteUserByUids(Integer[] ids) {
        userService.deleteUserByids(ids);
        return RestResponse.success();
    }
}
