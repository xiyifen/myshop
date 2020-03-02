package com.xiyifen.myshop.common.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@Configuration
public class ResourceConfigAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${upload.imagePath}")
    private String mImagesPath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("1024MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    /**
     * 这里是映射文件路径的方法
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        addResourceHandler 文件的访问路径，addResourceLocations 映射到真实路径
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+mImagesPath);
        super.addResourceHandlers(registry);
    }
}
