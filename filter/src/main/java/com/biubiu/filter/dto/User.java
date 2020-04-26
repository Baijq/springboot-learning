package com.biubiu.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * User
 *
 * @author biubiu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "姓名不为空")
    private String userName;
    private Integer age;
    private String cityId;
    private String cityCode;
}
