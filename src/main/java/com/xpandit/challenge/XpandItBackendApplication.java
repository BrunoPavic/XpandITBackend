package com.xpandit.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Xpand challenge - MoviesAPI", version = "1.0.0", description = "API documentation for Xpand challenge"))
public class XpandItBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(XpandItBackendApplication.class, args);
	}

}
