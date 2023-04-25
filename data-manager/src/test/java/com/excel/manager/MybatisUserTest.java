package com.excel.manager;

import com.excel.manager.dao.JobDao;
import com.excel.manager.dao.UserDao;
import com.excel.manager.pojo.TbJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
public class MybatisUserTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAll() {
//        List<TbUserTest> userList = userMapper.selectList(null);
        System.out.println(userDao.selectList());
//        System.out.println(userList);
    }

    @Autowired
    private JobDao jobDao;

    @Test
    public void traversalJobs() {
//        TbJob job = jobDao.getById(1200);
        TbJob job = new TbJob();

        Field[] fields = job.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName());
            } catch (Exception ignored) {
            }
        }
    }
}
