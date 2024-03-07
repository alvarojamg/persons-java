package com.example.persons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SpringBootApplication
public class PersonsApplication {
	private static final Logger logger = LogManager.getLogger(PersonsApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PersonsApplication.class, args);
		logger.info("Application started");
	}


}
