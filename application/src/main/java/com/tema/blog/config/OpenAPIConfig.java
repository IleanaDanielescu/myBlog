package com.tema.blog.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My Blog API"))
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP
	, scheme = "basic")

public class OpenAPIConfig {

}
