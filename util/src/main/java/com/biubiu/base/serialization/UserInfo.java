package com.biubiu.base.serialization;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5744073591975572252L;

    private String name;
    /** transient修饰的属性不会被序列化 **/
    private transient String pwd;

    public UserInfo() {
    }

    public UserInfo(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
