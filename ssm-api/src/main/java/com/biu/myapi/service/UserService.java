package com.biu.myapi.service;

import com.biu.myapi.entity.User;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author wbbaijq
 */
public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    List<User> find(User user);

    User save(User user);

    void remove(Integer id);

    void delete(Integer id);

    User update(User user);

}