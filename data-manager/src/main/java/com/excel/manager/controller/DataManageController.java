package com.excel.manager.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.excel.enums.ResultCode;
import com.excel.manager.config.DataSourceConfig;
import com.excel.manager.config.PatternProperties;
import com.excel.manager.pojo.TbJob;
import com.excel.manager.service.TbJobService;
import com.excel.vojo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            System.out.println(e);
        }
        return null;
    }

    @GetMapping("/getAllJob")
    public List<TbJob> getAllJob() {
        return tbJobService.getAllJob();
    }
}