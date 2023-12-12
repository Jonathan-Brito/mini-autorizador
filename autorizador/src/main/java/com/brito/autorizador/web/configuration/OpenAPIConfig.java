package com.brito.autorizador.web.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("#{systemProperties['openapi.dev-url'] ?: 'http://localhost:8080'}")
    private String devUrl;

    @Bean
    public OpenAPI attornatusOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL do servidor no ambiente de Desenvolvimento");

        Contact contact = new Contact();
        contact.setEmail("devjonathanbrito@gmail.com");
        contact.setName("Jonathan Brito");

        License mit = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Mini-autorizador - API")
                .version("1.0")
                .contact(contact)
                .description("Teste Técnico Back End").termsOfService("")
                .license(mit);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
