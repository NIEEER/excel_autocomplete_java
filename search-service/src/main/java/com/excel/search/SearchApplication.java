package com.excel.search;

import com.excel.feign.client.DataManagerClient;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(clients = {DataManagerClient.class})
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

//    @Bean
//    public RestHighLevelClient client() {
//        return new RestHighLevelClient(RestClient.builder(HttpHost.create("http://192.168.43.228:9200")));
//    }
}