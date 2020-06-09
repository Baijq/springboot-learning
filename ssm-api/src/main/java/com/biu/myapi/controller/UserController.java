package com.biu.myapi.controller;

import com.biu.myapi.controller.request.AddUserRequest;
import com.biu.myapi.controller.request.EditUserRequest;
import com.biu.myapi.controller.request.QueryUserRequest;
import com.biu.myapi.controller.response.RestResponse;
import com.biu.myapi.entity.User;
import com.biu.myapi.service.UserService;
import com.biu.myapi.util.ParamHelper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * UserController
 *
 * @author wbbaijq
 */

@RestController
@RequestMapping("/api-info/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public RestResponse getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return RestResponse.failure("查询失败");
        } else {
            return RestResponse.success("查询成功").setData(user);
        }
    }

    @GetMapping
    public Map<String, Object> list(QueryUserRequest request) {
        List<User> userList = null;
        if (request == null) {
            userList = userService.findAll();
        } else {
            userList = userService.find(ParamHelper.converter(request));
        }
        if (CollectionUtils.isEmpty(userList)) {
            return RestResponse.failure("查询失败");
        } else {
            return RestResponse.success("查询成功").setData(userList);
        }
    }

    @Delete("/{id}")
    public Map<String, Object> remove(@PathVariable("id") Integer id, @RequestParam("db") Boolean db) {
        if (id == null) {
            return RestResponse.failure("id为必传参数");
        }

        if (db != null && db) {
            userService.delete(id);
        } else {
            userService.remove(id);
        }
        return RestResponse.success("删除成功");
    }

    @PostMapping
    public Map<String, Object> save(@RequestBody AddUserRequest request) {
        User user = userService.save(ParamHelper.converter(request));
        if (user != null) {
            return RestResponse.success("添加成功").setData(user);
        } else {
            return RestResponse.failure("添加失败");
        }
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@RequestBody EditUserRequest request, @PathVariable("id") Integer id) {
        if (id == null && request == null) {
            return RestResponse.failure("id为必传参数");
        }
        User user = userService.update(ParamHelper.converter(request, id));
        if (user != null) {
            return RestResponse.success("修改成功").setData(user);
        } else {
            return RestResponse.failure("修改失败");
        }
    }

}
