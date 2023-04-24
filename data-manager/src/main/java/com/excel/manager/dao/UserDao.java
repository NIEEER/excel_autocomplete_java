package com.excel.manager.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excel.manager.pojo.TbUserTest;

import java.util.List;

public interface UserDao extends IService<TbUserTest> {
    List<TbUserTest> selectList();
}
