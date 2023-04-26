package com.excel.manager.vojo;

import com.excel.manager.pojo.TbJob;
import lombok.Data;

import java.util.List;

@Data
public class TbJobsPageResult {
    private Long totalPages;
    private List<TbJob> jobList;
}
