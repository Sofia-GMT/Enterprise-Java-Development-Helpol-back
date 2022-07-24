package com.ironhack.primersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PrimersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimersServiceApplication.class, args);
	}

}
