package com.xiyifen.myshop.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "xiyifen.swagger")
@PropertySource(value = "application.properties")
public class SwaggerProperty {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String name;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;

}
