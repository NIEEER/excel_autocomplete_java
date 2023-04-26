package com.excel.manager;

import com.excel.manager.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisUserTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAll(){
//        List<TbUserTest> userList = userMapper.selectList(null);
        System.out.println(userDao.selectList());
//        System.out.println(userList);
    }
}
