package com.xiyifen.myshop.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "xiyifen.shiro")
@PropertySource(value = "classpath:application.properties")
public class ShiroProperty {
    private String anonUrl;
    private Integer jwtTimeOut;
}
