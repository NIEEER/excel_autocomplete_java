package com.excel.manager.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excel.manager.mapper.UserMapper;
import com.excel.manager.pojo.TbUserTest;
import com.excel.manager.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl extends ServiceImpl<UserMapper, TbUserTest> implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<TbUserTest> selectList() {

        return userMapper.selectList(null);
    }
}
