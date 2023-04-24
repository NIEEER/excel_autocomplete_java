package com.excel.search.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class ElasticSearchProperties {
    @Value("${client.elasticSearchHost}")
    private String elasticSearchHost;
}
