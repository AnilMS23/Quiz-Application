package com.anil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiuceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiuceRegistryApplication.class, args);
	}

}
