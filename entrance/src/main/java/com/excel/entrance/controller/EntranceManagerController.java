package com.excel.entrance.controller;

import com.excel.entrance.service.EntranceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntranceManagerController {

    @Autowired
    private EntranceManagerService managerService;

    @GetMapping("now")
    public String now(@RequestHeader(value = "User", required = false) String header) {
        System.out.println(header);
        return managerService.now();
    }
}
