package com.excel.entrance;

import com.excel.feign.client.DataManagerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients(basePackages = "com.excel.feign.client")
@EnableFeignClients(clients = {DataManagerClient.class})
public class EntranceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EntranceApplication.class, args);
    }
}
