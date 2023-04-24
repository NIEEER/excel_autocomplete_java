package com.excel.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "pattern")
@Data
@Component
public class PatternProperties {
    private String dataFormat;
}
