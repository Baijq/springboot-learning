package com.biubiu.base.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 * @description TODO
 * @date 2019/5/29
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Student {

    private long id;

    private String name;

    private int age;

    private int grade;

    private String major;

    private String school;

}
