package com.testing.test_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Shop Backend", version = "1.0.0", description = "Shop Backend made with Java - Spring Boot App", contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Shop App", email = "markyasser2011@gmail.com")))
public class TestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);
	}
}
