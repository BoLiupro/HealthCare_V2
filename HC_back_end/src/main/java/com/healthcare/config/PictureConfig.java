package com.healthcare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class PictureConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/hcpic/**").addResourceLocations("file:/Users/liubo/Desktop/hcpic/");
        registry.addResourceHandler("/hcpho/**").addResourceLocations("file:/Users/liubo/Desktop/hcpho/");
    }
}
