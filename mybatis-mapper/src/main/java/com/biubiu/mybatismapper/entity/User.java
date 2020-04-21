package com.biubiu.mybatismapper.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author biubiu
 */
@Data
@Table(name = "tb_users")
public class User implements Serializable {
    private static final long serialVersionUID = 3167212910028834735L;

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;
    private String tel;
    private String address;

    private Date createTime;
    @Transient//这个注解表示，查询的时候不查询phone这个字段
    private Date updateTime;
    private Integer valid;
}
