package com.biubiu.swagger.service;

import com.biubiu.swagger.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * DbService
 *
 * @author baijq
 */
@Service
public class DbService {

    private static final List<User> users = new ArrayList<>();

    public List<User> listAllUser() {
        return initUser();
    }

    public static List<User> initUser() {
        User u1 = new User();
        u1.setId(UUID.randomUUID().toString());
        u1.setName("辛巴");
        u1.setAge(20);
        u1.setTel("15266569639");
        u1.setAddress("上海浦东新区世纪大道");

        User u2 = new User();
        u2.setId(UUID.randomUUID().toString());
        u2.setName("灰太狼");
        u2.setAge(18);
        u2.setTel("13696255241");
        u2.setAddress("青青草原狼堡");

        User u3 = new User();
        u3.setId(UUID.randomUUID().toString());
        u3.setName("奥特曼");
        u3.setAge(22);
        u3.setTel("1566666622");
        u3.setAddress("巨人国奥特曼镇");
        return Arrays.asList(u1, u2, u3);
    }

}
