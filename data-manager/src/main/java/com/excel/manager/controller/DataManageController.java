package com.excel.manager.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.excel.enums.ResultCode;
import com.excel.manager.config.DataSourceConfig;
import com.excel.manager.config.PatternProperties;
import com.excel.manager.pojo.TbJob;
import com.excel.manager.service.TbJobService;
import com.excel.manager.vojo.TbJobsPageResult;
import com.excel.manager.vojo.TbJobsPageResultView;
import com.excel.util.EntityReflectionUtils;
import com.excel.util.JobChineseEnglishFieldTranslator;
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
import java.util.Map;

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
            // 从Spring容器中获取到dataSourceConfig、myDruidDataSource对象实例。
            DataSourceConfig dataSourceConfig = (DataSourceConfig) applicationContext.getBean("dataSourceConfig");
            DruidDataSource dataSource = (DruidDataSource) applicationContext.getBean("myDruidDataSource");
            //FIXME 经初步测试，在生产环境下，此处强行关闭连接池可能会导致一些未知错误，具体错误逻辑待进一步测试
            // -不做修改，则可通过消息队列进行提前限流处理, 或测试后再作调整
            dataSource.close();
            // 修改连接池信息后重启连接池
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

    @PostMapping("/uploadTableData")
    public CommonResult uploadTableData(@RequestBody String tableData) {
        Map tableDataMap = JSON.parseObject(tableData, HashMap.class);
        List<Map<String, String>> mapList = (List<Map<String, String>>) tableDataMap.get("tableData");
        List<TbJob> jobs = new ArrayList<>();
        for (Map<String, String> map : mapList) {
            TbJob job = new TbJob();
            for (Map.Entry entry : map.entrySet()) {
                try {
                    EntityReflectionUtils.setFieldValue(job,
                            JobChineseEnglishFieldTranslator.translate(entry.getKey().toString()), entry.getValue().toString());
                } catch (Exception ignored) {

                }
            }
            jobs.add(job);
        }
        //TODO 此处应发起远过程调用，通知搜索引擎变更数据，在集成消息服务后处理
        System.out.println(jobs.size());
        return CommonResult.success();
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
            // 因为pojo的字段名称为英文，表格的列名为中文。为方便前端解析，此处需对字段名称进行中英映射
            List<TbJob> jobs = jobsPageResult.getJobList();
            List<HashMap<String, String>> mapList = getTableListMaps(jobs, true);
            // 对结果进行封装
            TbJobsPageResultView resultView = new TbJobsPageResultView();
            resultView.setTotalPages(jobsPageResult.getTotalPages());
            resultView.setJobList(mapList);
            return CommonResult.success(resultView);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failure(ResultCode.FAILURE);
        }
    }

    @GetMapping("/getOneEmptyData")
    public CommonResult getOneEmptyData() {
        List<TbJob> jobs = new ArrayList<>();
        TbJob job = new TbJob();
        jobs.add(job);
        List<HashMap<String, String>> mapList = getTableListMaps(jobs, false);
        // 对结果进行封装
        TbJobsPageResultView resultView = new TbJobsPageResultView();
        resultView.setJobList(mapList);
        return CommonResult.success(resultView);
    }

    private static List<HashMap<String, String>> getTableListMaps(List<TbJob> jobs, Boolean includeId) {
        List<HashMap<String, String>> mapList = new ArrayList<>();
        for (TbJob job : jobs) {
            HashMap<String, String> map = new HashMap<>();
            // 获取对象的字段信息
            Field[] fields = job.getClass().getDeclaredFields();
            // 遍历对象所有字段
            for (Field field : fields) {
                // 对象的字段为私有的，需要将字段名称设置为可访问，否则无法反射获取字段值
                field.setAccessible(true);
                try {
                    // id没有中文字段作为映射
                    if (includeId && field.getName().equals("id")) {
                        map.put("id", field.get(job).toString());
                    } else {
                        // 通过反射得到字段的名称和值，并把名称映射为中文
                        map.put(JobEnglishChineseFieldTranslator.translate(field.getName()), field.get(job).toString());
                    }
                } catch (IllegalAccessException | NullPointerException ignored) {
                    /*字段值可能为null，此处直接跳过不做处理*/
                }
            }
            mapList.add(map);
        }
        return mapList;
    }
}