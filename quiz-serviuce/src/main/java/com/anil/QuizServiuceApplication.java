package com.anil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizServiuceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiuceApplication.class, args);
	}

}
