package com.comanda_digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComandaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComandaDigitalApplication.class, args);
	}

}
