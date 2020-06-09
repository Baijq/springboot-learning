package com.biu.myapi.controller.dto;

import lombok.Data;

/**
 * UserDto
 *
 * @author wbbaijq
 */
@Data
public class UserDto {

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String mail;
    private String reward;
    private String avatar;
    private Integer state;
    
}

