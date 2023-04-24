package com.excel.search.controller;

import com.excel.feign.pojo.TbJob;
import com.excel.search.service.SearchDataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searchManager")
public class SearchDataManagerController {
    @Autowired
    SearchDataManagerService searchDataManagerService;

    @GetMapping("/id")
    public TbJob getJobById(@RequestParam String id){
        System.out.println(id);
        return searchDataManagerService.getJobById(id);
    }
}
