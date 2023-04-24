package com.excel.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.excel.manager.pojo.TbJob;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobMapper extends BaseMapper<TbJob> {
}
