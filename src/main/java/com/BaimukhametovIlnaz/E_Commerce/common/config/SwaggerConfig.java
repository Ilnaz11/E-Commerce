package com.BaimukhametovIlnaz.E_Commerce.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Backend API")
                        .description("REST API для аутентификации, каталога, корзины и заказов.")
                        .version("1.0.0"));
    }
}
