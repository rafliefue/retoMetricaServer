package com.everis.bootcamp.patronesServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PatronesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatronesServerApplication.class, args);
	}

}
