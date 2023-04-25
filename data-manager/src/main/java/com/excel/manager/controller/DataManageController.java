package com.excel.manager.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.excel.enums.ResultCode;
import com.excel.manager.config.DataSourceConfig;
import com.excel.manager.config.PatternProperties;
import com.excel.manager.pojo.TbJob;
import com.excel.manager.service.TbJobService;
import com.excel.manager.vojo.TbJobsPageResult;
import com.excel.manager.vojo.TbJobsPageResultView;
import com.excel.util.JobEnglishChineseFieldTranslator;
import com.excel.vojo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dataManager")
public class DataManageController {
    @Autowired
    private PatternProperties properties;
    @Autowired
    private TbJobService tbJobService;
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("refreshNow")
    public String now() {
        try {
            DataSourceConfig dataSourceConfig = (DataSourceConfig) applicationContext.getBean("dataSourceConfig");
            DruidDataSource dataSource = (DruidDataSource) applicationContext.getBean("myDruidDataSource");
            dataSource.close();
            dataSource.setUrl(dataSourceConfig.getUrl());
            dataSource.setUsername(dataSourceConfig.getUsername());
            dataSource.setPassword(dataSourceConfig.getPassword());
            dataSource.restart();
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDataFormat()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/upload")
    public CommonResult importPersons(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            tbJobService.importFromExcel(excelFile);
            return CommonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failure(ResultCode.FAILURE);
        }
    }

    @GetMapping("/fetchSuggestions")
    public CommonResult fetchSuggestions(String inputValue, String columnName) {
        return CommonResult.success(tbJobService.fetchSuggestions(inputValue, columnName));
    }


    @GetMapping("/id")
    public TbJob getJobById(String id) {
        try {
            return tbJobService.getJobById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getAllJob")
    public List<TbJob> getAllJob() {
        return tbJobService.getAllJob();
    }

    @GetMapping("/getJobListByPage")
    public CommonResult getJobListByPage(Integer currentPage, Integer pageSize) {
        try {
            TbJobsPageResult jobsPageResult = tbJobService.getJobListByPage(currentPage, pageSize);
            List<TbJob> jobs = jobsPageResult.getJobList();
            List<HashMap<String, String>> mapList = new ArrayList<>();
            for (TbJob job : jobs) {
                HashMap<String, String> map = new HashMap<>();
                Field[] fields = job.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!field.getName().equals("id")) {
                        field.setAccessible(true);  // 允许访问私有变量
                        // 反射拿到字段的名称和值，并把名称映射为中文
                        try {
                            map.put(JobEnglishChineseFieldTranslator.translate(field.getName()), field.get(job).toString());
                        } catch (IllegalArgumentException | NullPointerException ignored) {

                        }
                    }
                }
                mapList.add(map);
            }
            TbJobsPageResultView resultView = new TbJobsPageResultView();
            resultView.setTotalPages(jobsPageResult.getTotalPages());
            resultView.setJobList(mapList);
            return CommonResult.success(resultView);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failure(ResultCode.FAILURE);
        }
    }
}