package com.xiyifen.myshop.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "xiyifen.swagger")
//此处之所以要加classpath,是因为如果不加的话,在junit 测试中会找不到application.properties
@PropertySource(value = "classpath:application.properties")
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
