package com.onlinebookreadingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

	private String schemeName = "JWT AUTHENTICATION";
	private String bearerFormat = "JWT";
	private String scheme = "bearer";
	private String description = "Please enter the JWT token only don't write \"Bearer\" here!!";

	@Bean
	public OpenAPI caseOpenAPI() {
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(schemeName))
				.components(new Components().addSecuritySchemes(schemeName,
						new SecurityScheme().name(schemeName).type(SecurityScheme.Type.HTTP).bearerFormat(bearerFormat)
								.in(SecurityScheme.In.HEADER).scheme(scheme).description(description)))
				.info(new Info().title("ONLINE BOOK READING APP").description("PROJECT FOR ONLINE BOOK READING APP")
						.version("v0.0.1").license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
