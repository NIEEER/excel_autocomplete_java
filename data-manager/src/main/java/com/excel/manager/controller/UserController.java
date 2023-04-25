package com.excel.manager.controller;

import com.excel.manager.dao.ChineseUserDao;
import com.excel.manager.pojo.TbUserTest;
import com.excel.manager.dao.UserDao;
import com.excel.manager.pojo.TbUserTestChinese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/dataManager")
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ChineseUserDao chineseUserDao;

    @RequestMapping("/selectList")
    public List<TbUserTest> selectList(){
        return userDao.selectList();
    }

    @RequestMapping("/selectChineseUserList")
    public List<TbUserTestChinese> selectChineseUserList(){
        return chineseUserDao.list();
    }
}
