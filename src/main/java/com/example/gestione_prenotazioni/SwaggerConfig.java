package com.example.gestione_prenotazioni;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        try {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.gestioneprenotazioni"))
                    .paths(PathSelectors.any())
                    .build();
        } catch (Exception e) {
            // Handle exception or log error
            throw new RuntimeException("Failed to create Docket for Swagger configuration", e);
        }
    }
}