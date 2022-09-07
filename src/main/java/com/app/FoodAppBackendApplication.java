package com.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodAppBackendApplication.class, args);
	}

	@Bean
	public ModelMapper mapper()
	{
		System.out.println("In Model Mapper!!!!");
		return new ModelMapper();
	}
}
