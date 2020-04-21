package com.biubiu.mybatismapper.service.impl;

import com.biubiu.mybatismapper.entity.User;
import com.biubiu.mybatismapper.mapper.UserMapper;
import com.biubiu.mybatismapper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

/**
 * UserServiceImpl
 *
 * @author biubiu
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserByid(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void deleteUserByids(Integer[] ids) {
        Stream.of(ids).forEach(userMapper::deleteByPrimaryKey);
    }
}
