//package com.excel.manager.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
//@Component
//@RefreshScope
//public class MybatisConfig {
//    @Autowired
//    private DataSourceConfig config;
//
//    @Bean
//    @RefreshScope
//    public DataSource dataSource() {
//        DruidDataSource dataSource = null;
//        try {
//            dataSource = new DruidDataSource();
//            dataSource.setUrl(config.getUrl());
//            dataSource.setUsername(config.getUsername());
//            dataSource.setPassword(config.getPassword());
//            dataSource.setDriverClassName(config.getDriverClassName());
//            return dataSource;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
