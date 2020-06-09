package com.biu.myapi.mapper;

import com.biu.myapi.entity.User;

import java.util.List;

/**
 * UserMapper
 *
 * @author wbbaijq
 */
public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    List<User> find(User user);

    void save(User user);

    void update(User user);

    void delete(Integer id);

    void remove(Integer id);
}
