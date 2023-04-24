package com.excel.search.service;

import com.excel.feign.client.DataManagerClient;
import com.excel.feign.pojo.TbJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchDataManagerService {
    @Autowired
    DataManagerClient dataManagerClient;

    public TbJob getJobById(String id) {
        return dataManagerClient.getJobById(id);
    }
}
