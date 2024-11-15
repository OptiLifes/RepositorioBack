package com.optilife.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {

    @Value("${optilife.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        //Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development server");

        //Información de contacto
        Contact contact = new Contact();
        contact.setEmail("julval09@gmail.com");
        contact.setName("Optilife");
        contact.setUrl("https://www.optilife.com");

        License mitlicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        //Información general de la API
        Info info = new Info()
                .title("API Optilife App para el control de la salud")
                .version("1.0")
                .contact(contact)
                .description("Esta es una API para el control de la salud de los usuarios de Optilife")
                .termsOfService("https://www.optilife.com/terms")
                .license(mitlicense);

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}
