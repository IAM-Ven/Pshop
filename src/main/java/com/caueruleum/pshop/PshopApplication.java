package com.caueruleum.pshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PshopApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(PshopApplication.class, args);
	}

}
