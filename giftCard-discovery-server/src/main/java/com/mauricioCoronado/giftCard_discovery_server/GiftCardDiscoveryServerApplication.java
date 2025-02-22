package com.mauricioCoronado.giftCard_discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GiftCardDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftCardDiscoveryServerApplication.class, args);
	}

}
