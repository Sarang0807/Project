package com.app;

import java.io.File;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodAppBackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FoodAppBackendApplication.class, args);
	}

	@Bean
	public ModelMapper mapper()
	{
		System.out.println("In Model Mapper!!!!");
		return new ModelMapper();
	}
	
	@Value("${file.upload.location}")//SpEL
	private String folderName;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("In Runner Method : "+folderName);
		//create image folder if it does not exists
		File dir = new File(folderName);
		if(!dir.exists())
		{
			System.out.println("Created Folder/s : "+dir.mkdirs());
			
		}else {
			System.out.println("Folder Exists Already");
		}
	}
	
}
