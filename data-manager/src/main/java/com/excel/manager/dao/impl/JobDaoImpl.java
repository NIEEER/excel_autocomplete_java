package com.excel.manager.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.excel.manager.dao.JobDao;
import com.excel.manager.mapper.JobMapper;
import com.excel.manager.pojo.TbJob;
import com.excel.util.EntityReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.excel.util.EntityReflectionUtils.getFieldValue;

@Service
public class JobDaoImpl extends ServiceImpl<JobMapper, TbJob> implements JobDao {
    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<String> getColumnValues(String columnName) {
        QueryWrapper<TbJob> wrapper = new QueryWrapper<>();
//        wrapper.select(columnName); // 指定要查询的字段
        wrapper.select(EntityReflectionUtils.camelToUnderline(columnName)); // 指定要查询的字段
        List<TbJob> jobList = jobMapper.selectList(wrapper);
        List<String> columnValueList = new ArrayList<>();
        for (TbJob job : jobList) {
            String fieldValue = Objects.requireNonNull(getFieldValue(job, columnName)).toString();
            columnValueList.add(fieldValue);
        }
        return columnValueList; // 返回 field 字段的值
    }

    @Override
    public List<TbJob> getJobListByPage(Integer currentPage, Integer pageSize) {
        Page<TbJob> page = new Page<>(currentPage, pageSize);
        QueryWrapper<TbJob> queryWrapper = new QueryWrapper<>();
//        queryWrapper.
        jobMapper.selectPage(page, queryWrapper);
        return page.getRecords();
    }
}
