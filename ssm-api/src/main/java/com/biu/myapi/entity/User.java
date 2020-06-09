package com.biu.myapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * User
 *
 * @author wbbaijq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String mail;
    private Integer state;
    private String reward;
    private String avatar;
    private Date createTime;
    private Date updateTime;

}
