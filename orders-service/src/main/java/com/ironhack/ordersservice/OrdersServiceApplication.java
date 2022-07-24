package com.ironhack.ordersservice;

import com.ironhack.ordersservice.enums.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrdersServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(OrdersServiceApplication.class, args );
	}


	@Override
	public void run(String... args) throws Exception {
		Status status = Status.valueOf( "DELIVERED" );
		System.out.println(status.name());
	}
}
