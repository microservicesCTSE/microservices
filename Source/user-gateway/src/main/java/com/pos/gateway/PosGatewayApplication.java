package com.pos.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PosGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosGatewayApplication.class, args);
	}

}
