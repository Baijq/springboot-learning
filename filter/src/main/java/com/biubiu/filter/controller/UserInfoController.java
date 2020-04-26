package com.biubiu.filter.controller;

import com.biubiu.filter.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户测试 Controller
 *
 * @author biubiu
 */
@RestController
public class UserInfoController {

    @GetMapping("/user")
    public List<User> getUser(User user) {
        //初始化数据
        List<User> users = new ArrayList<>();
        users.add(User.builder().age(18).cityCode("541").cityId("01").userName("张红").build());
        users.add(User.builder().age(22).cityCode("21").cityId("12").userName("王东").build());
        users.add(User.builder().age(18).cityCode("541").cityId("01").userName("可云").build());
        users.add(User.builder().age(22).cityCode("21").cityId("12").userName("王迈东").build());
        users.add(User.builder().age(18).cityCode("541").cityId("01").userName("欧阳倩").build());
        users.add(User.builder().age(22).cityCode("25").cityId("12").userName("赵菲").build());
        //根据条件查询
        return users.stream().filter(x -> user.getAge() != null ? ((int) user.getAge() == (int) x.getAge()) : true)
                .filter(x -> user.getCityCode() != null ? (user.getCityCode().equals(x.getCityCode())) : true)
                .filter(x -> user.getCityId() != null ? user.getCityId().equals(x.getCityId()) : true)
                .filter(x -> user.getUserName() != null ? user.getUserName().equals(x.getUserName()) : true)
                .collect(Collectors.toList());
    }

}
