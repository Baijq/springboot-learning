package com.api.example.apiexample.controller.request;

import lombok.Data;

/**
 * 请求参数
 *
 * @author wbbaijq
 */
@Data
public class UserParamVO {

    private Integer id;

    private String name;

    private String sex;

    private int age;
}
