package com.tkd.flight.mangement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class FlightManagementWithH2Application {

	public static void main(String[] args) {
		SpringApplication.run(FlightManagementWithH2Application.class, args);
	}

}
