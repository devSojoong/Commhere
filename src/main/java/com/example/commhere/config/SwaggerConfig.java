package com.example.commhere.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("Commhere")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI OpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Commhere")
                        .description("Commhere 프로젝트 API 명세서입니다.")
                        .version("v.0.0.1"));
    }
}
