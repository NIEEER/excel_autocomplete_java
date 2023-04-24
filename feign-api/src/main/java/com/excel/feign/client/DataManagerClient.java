package com.excel.feign.client;

import com.excel.feign.config.DefaultFeignConfiguration;
import com.excel.feign.pojo.TbJob;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "datamanager",
        configuration = DefaultFeignConfiguration.class)
@RequestMapping("dataManager")
public interface DataManagerClient {

    @GetMapping("/now")
    String now();

    @GetMapping("/id")
    TbJob getJobById(@RequestParam String id);

    @GetMapping("/getAllJob")
    List<TbJob> getAllJob();
}
