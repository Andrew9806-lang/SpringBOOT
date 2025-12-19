package org.example.carsharing.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//eto dlya otobrazhenii stratovoi stranici po documentacii po nashemu projectu
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Car-Sharing-API")
                        .version("v1")
                        .description("Документация REST-эндпоинтов проекта Car-Sharing"));

    }

}
