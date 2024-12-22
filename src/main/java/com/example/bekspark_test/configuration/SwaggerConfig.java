package com.example.bekspark_test.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Socks API")
                        .description("API for accounting of socks in a store warehouse")
                        .version("1.0.0"));
    }
}