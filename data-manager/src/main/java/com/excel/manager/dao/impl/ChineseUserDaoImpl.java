package com.excel.manager.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excel.manager.dao.ChineseUserDao;
import com.excel.manager.mapper.ChineseUserMapper;
import com.excel.manager.pojo.TbUserTestChinese;
import org.springframework.stereotype.Service;

@Service
public class ChineseUserDaoImpl extends ServiceImpl<ChineseUserMapper, TbUserTestChinese> implements ChineseUserDao {
}
