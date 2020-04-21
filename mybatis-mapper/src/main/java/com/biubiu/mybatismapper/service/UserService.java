package com.biubiu.mybatismapper.service;

import com.biubiu.mybatismapper.entity.User;

import java.util.List;

/**
 * UserService
 *
 * @author biubiu
 */
public interface UserService {

    /**
     * 查询所有用户
     */
    List<User> queryAllUser();

    /**
     * 根据用户id查询用户
     */
    User queryUserById(Integer id);

    /**
     * 根据用户id修改用户信息
     */
    void updateUserByid(User user);

    /**
     * 添加用户
     */
    void addUser(User user);

    /**
     * 根据用户id删除用户
     */
    void deleteUserByids(Integer[] ids);
}
