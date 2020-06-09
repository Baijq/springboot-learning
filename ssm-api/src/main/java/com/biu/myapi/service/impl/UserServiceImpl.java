package com.biu.myapi.service.impl;

import com.biu.myapi.entity.User;
import com.biu.myapi.mapper.UserMapper;
import com.biu.myapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author wbbaijq
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> find(User user) {
        return userMapper.find(user);
    }

    @Override
    public User save(User user) {
        userMapper.save(user);
        if (user.getId() != null) {
            return userMapper.findById(user.getId());
        }
        return null;
    }

    @Override
    public void remove(Integer id) {
        userMapper.remove(id);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public User update(User user) {
        userMapper.update(user);
        if (user.getId() != null) {
            return userMapper.findById(user.getId());
        }
        return null;
    }


}
