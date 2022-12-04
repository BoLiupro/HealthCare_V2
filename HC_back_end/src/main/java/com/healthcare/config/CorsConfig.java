package com.healthcare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
         * allowedOrigins：允许访问的url，可以固定单条或者多条内容，如："http://www.baidu.com"。
         * allowedMethods：允许的请求方式，如：POST、GET、PUT、DELETE等。
         * allowCredentials 是否发送cookie
         * maxAge：配置预检请求的有效时间
         * allowedHeaders：允许的请求header，可以自定义设置任意请求头信息。
         */
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
