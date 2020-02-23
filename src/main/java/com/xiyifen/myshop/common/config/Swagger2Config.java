package com.xiyifen.myshop.common.config;

import com.xiyifen.myshop.common.property.ShiroProperty;
import com.xiyifen.myshop.common.property.SwaggerProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private SwaggerProperty swaggerProperties;
    @Autowired
    private ShiroProperty shiroProperty;


//    public static final String SWAGGER_SCAN_BASE_PACKAGE="com.xiyifen.myshop";
//    public static final String VERSION = "1.0.0";
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
//                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
//                .build();
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("商品后台管理") //设置文档的标题
//                .description("商品后台管理API API 接口文档") // 设置文档的描述
//                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
//                .termsOfServiceUrl("http://www.baidu.com") // 设置文档的License信息->1.3 License information
//                .build();
//    }

    @Bean
    public Docket swaggerApi() {
        // 给swaggerUI 添加token输入框
//        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(new ParameterBuilder()
//                .name("Authentication")
//                .description("认证token")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build());

//        SwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
//                .apiInfo(apiInfo()).globalOperationParameters(parameters);
    }

    /**
     * swagger 简介信息
     * @return
     */
    private ApiInfo apiInfo() {

        return new ApiInfo( swaggerProperties.getTitle(),
                                    swaggerProperties.getDescription(),
                                    swaggerProperties.getVersion(),
                                    null,
                                    new Contact(swaggerProperties.getName(), swaggerProperties.getUrl(), swaggerProperties.getEmail()),
                swaggerProperties.getLicense(), swaggerProperties.getLicenseUrl(), Collections.emptyList());
    }
}

