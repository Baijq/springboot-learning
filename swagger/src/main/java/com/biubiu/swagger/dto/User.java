package com.biubiu.swagger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author baijq
 */
//实体类与json互转的时候 属性值为null的不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "User", description = "用户实体类")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty(value = "用户Id", example = "0c7b6416-baf0-4dba-b98b-95186c739e56", dataType = "String")
    private String id;
    @ApiModelProperty(value = "姓名", example = "张三", dataType = "String")
    private String name;
    @ApiModelProperty(value = "年龄", example = "20", dataType = "Integer")
    private Integer age;
    /**
     * 作用是把该属性的名称序列化为另外一个名称
     */
    @JsonProperty(value = "telephone")
    @ApiModelProperty(value = "姓名", example = "15569696363", dataType = "String")
    private String tel;
    @ApiModelProperty(value = "地址", example = "雨巷，成都街头", dataType = "String")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
