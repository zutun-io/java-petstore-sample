package io.zutun.samples.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "${doc.api.title}",
                version = "${doc.api.version}",
                description = "${doc.api.description:Zutun Petstore Sample API}",
                contact = @Contact(
                        name = "${doc.api.contact.name}",
                        email = "${doc.api.contact.email}",
                        url = "${doc.api.contact.url}"
                )
        )
)
public class OpenAPIDocConfig {

    @Bean
    public OpenAPI customDocumentation() {
        return new OpenAPI();
    }

}
