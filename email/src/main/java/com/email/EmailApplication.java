package com.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EmailApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(EmailApplication.class, args);
	}

}
