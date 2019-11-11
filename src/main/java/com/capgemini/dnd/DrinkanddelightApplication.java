package com.capgemini.dnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.capgemini.dnd.controller", "com.capgemini.dnd.service", "com.capgemini.dnd.dao", "com.capgemini.dnd.entity"}) 
public class DrinkanddelightApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrinkanddelightApplication.class, args);
	}

}
