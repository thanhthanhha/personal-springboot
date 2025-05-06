package com.minibank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.minibank.**.repository")
public class MinibankWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinibankWebApplication.class, args);
	}
}
