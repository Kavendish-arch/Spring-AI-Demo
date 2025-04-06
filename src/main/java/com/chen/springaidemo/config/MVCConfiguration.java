package com.chen.springaidemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.config
* @className com.chen.springaidemo.config.MVCConfiguration

* @author chenyingtao
* @date 2025/4/4 14:38
* @version 1.0
* @description @todo 
*/
@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

    public MVCConfiguration() {
        System.out.println("MVCConfiguration");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
//                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*")
                .exposedHeaders("Content-Disposition"); // 暴露 Content-Disposition 头
    }

}
