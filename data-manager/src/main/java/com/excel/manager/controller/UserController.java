package com.excel.manager.controller;

import com.excel.manager.pojo.TbUserTest;
import com.excel.manager.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/selectList")
    public List<TbUserTest> selectList(){
        return userDao.selectList();
    }
}
