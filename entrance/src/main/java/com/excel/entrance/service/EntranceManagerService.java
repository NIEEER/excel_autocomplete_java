package com.excel.entrance.service;

import com.excel.feign.client.DataManagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntranceManagerService {
    @Autowired
    private DataManagerClient dataManagerClient;

    public String now() {
        return dataManagerClient.now();
    }
}
