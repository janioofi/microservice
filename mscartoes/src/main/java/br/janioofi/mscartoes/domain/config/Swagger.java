package br.janioofi.mscartoes.domain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("ms-api")
                .displayName("Microservice API")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservice API")
                        .description("API de cartoes")
                        .contact(new Contact()
                                .name("@janioofi")
                                .email("janioofi@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://bossabox/api/licenca")));
    }
}
