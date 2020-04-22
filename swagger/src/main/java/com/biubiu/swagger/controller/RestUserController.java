package com.biubiu.swagger.controller;

import com.biubiu.swagger.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * RestFul接口实例
 *
 * @author biubiu
 */
@Api(value = "RestUserController", description = "用户管理")
@RestController
@RequestMapping(value = "/users")
public class RestUserController {
    /**
     * 创建线程安全的map
     **/
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>() {
        {
            put(001L, User.builder().id(UUID.randomUUID().toString()).age(20).name("张撒谎").build());
            put(002L, User.builder().id(UUID.randomUUID().toString()).age(21).name("江江").build());
            put(003L, User.builder().id(UUID.randomUUID().toString()).age(20).name("鲁班").build());
        }
    });

    /**
     * 项目启动就会去执行该方法
     **/
    @PostConstruct
    private void init() {
        users.put(004L, User.builder().id(UUID.randomUUID().toString()).name("孙尚香").age(23).build());
        users.put(005L, User.builder().id(UUID.randomUUID().toString()).name("程咬金").age(30).build());
    }

    /**
     * 1.处理"/users/"的get请求，获取学生
     * 2.通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
     */
    @ApiOperation(value = "获取全部用户")
    @GetMapping(value = "/")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(users.values());
        return userList;
    }

    /**
     * 1.处理"/users/{id}"的GET请求，用来获取指定的学生
     * 2.url中的id可通过@PathVariable绑定到函数的参数中
     */
    @ApiOperation(value = "根据用户ID获取用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }


    /**
     * 1.处理"/users/"的post请求，添加学生
     * 2.@RequestBody从页面中传递参数直接映射userDto
     * 3.@RequestBody接受Map<String, String> map参数
     * 3.@RequestBody入参JSON化
     */
    @ApiOperation(value = "创建用户")
    @PostMapping(value = "/")
    public String postUser(@RequestBody User req) {
        User user = User.builder().id(req.getId()).name(req.getName().trim()).age(req.getAge()).build();
        users.put(Long.parseLong(user.getId()), user);
        return "success";
    }
}
