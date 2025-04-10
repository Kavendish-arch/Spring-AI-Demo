package com.chen.springaidemo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.config
* @className com.chen.springaidemo.config.SwaggerConfig

* @author chenyingtao
* @date 2025/4/10 15:54
* @version 1.0
* @description @todo 
*/
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI swaggerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("spring AI 人工智能助手")
                        .description("结合大模型的Web项目")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("设计文档").url(""));


    }
}
