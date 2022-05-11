package com.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PosDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosDiscoveryApplication.class, args);
	}

	
}
