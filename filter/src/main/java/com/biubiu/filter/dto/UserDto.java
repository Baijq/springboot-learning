package com.biubiu.filter.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * UserDto
 *
 * @author biubiu
 */
public class UserDto {

    @NotBlank(message = "姓名不为空")
    private String username;

    @NotBlank(message = "密码不为空")
    private String password;

    //嵌套必须加 @Valid，否则嵌套中的验证不生效
    @Valid
    @NotNull(message = "用户信息不能为空")
    private User userInfo;
}
