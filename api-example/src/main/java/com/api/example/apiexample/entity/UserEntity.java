package com.api.example.apiexample.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户实体
 *
 * @author wbbaijq
 */
@Data
@Accessors(chain = true)
public class UserEntity {

    private int uid;

    private String name;

    private String sex;

    private int age;

}
