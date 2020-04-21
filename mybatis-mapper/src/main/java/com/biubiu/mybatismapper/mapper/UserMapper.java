package com.biubiu.mybatismapper.mapper;

import com.biubiu.mybatismapper.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * UserMapper
 *
 * @author biubiu
 */
public interface UserMapper extends Mapper<User> {

    /** 根据电话号查询 */
    List<User> getUserByTel(String tel);
    /** 批量删除 */
    void deleteByIds(Integer[] ids);
}
