package com.codesnippet.spring_events_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringEventsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEventsDemoApplication.class, args);
	}

}
