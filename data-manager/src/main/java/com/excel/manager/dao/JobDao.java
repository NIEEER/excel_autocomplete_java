package com.excel.manager.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.excel.manager.pojo.TbJob;

import java.util.List;

public interface JobDao extends IService<TbJob> {
    List<String> getColumnValues(String columnName);

    List<TbJob> getJobListByPage(Integer currentPage, Integer pageSize);
}
