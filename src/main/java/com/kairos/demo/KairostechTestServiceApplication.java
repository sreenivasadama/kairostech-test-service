package com.kairos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableAutoConfiguration
@SpringBootConfiguration
@SpringBootApplication
public class KairostechTestServiceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(KairostechTestServiceApplication.class, args);
	}

}
