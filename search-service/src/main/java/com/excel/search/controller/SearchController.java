package com.excel.search.controller;

import com.excel.enums.ResultCode;
import com.excel.search.service.JobSearchService;
import com.excel.util.JobChineseEnglishFieldTranslator;
import com.excel.vojo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private JobSearchService jobSearchService;

    @GetMapping("/columnValueList")
    public CommonResult searchColumnValueList(String columnName, String inputValue) {
        columnName = JobChineseEnglishFieldTranslator.translate(columnName);
        List<String> valueList;
        try {
            if (inputValue.isEmpty()) {
                valueList = jobSearchService.searchColumnAllValueList(columnName);
            } else {
                valueList = jobSearchService.searchColumnValueList(columnName, inputValue);
            }
            if (valueList.isEmpty()) {
                return CommonResult.failure(ResultCode.DATA_IS_WRONG);
            } else {
                return CommonResult.success(ResultCode.SUCCESS, valueList);
            }
        } catch (Exception e) {
            return CommonResult.failure(ResultCode.FAILURE, e.toString());
        }
    }

    @GetMapping("/columnValueExist")
    public CommonResult searchColumnValueExist(String columnName, String inputValue) {
        columnName = JobChineseEnglishFieldTranslator.translate(columnName);
        try {
            Boolean isExist = jobSearchService.searchColumnValueExist(columnName, inputValue);
            return CommonResult.success(ResultCode.SUCCESS, isExist);
        } catch (IOException e) {
            return CommonResult.failure(ResultCode.FAILURE, e.toString());
        }
    }
}