package com.ld.poetize.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger文档配置
 *
 * @Author zuosy
 * @Date 2024/1/24 21:20
 **/
@Configuration
public class SwaggerConfig {

    // 设置 openapi 基础参数
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Poetize接口文档")
                        .contact(new Contact().name("zuosy").email("xxxx@163.com").url("https://www.baiodu.com"))
                        .version("2.0.0")
                        .description( "挑战最美博客Poetize2.0.0版本接口文档")
                        .license(new License().name("Apache 2.0")));
    }
}
