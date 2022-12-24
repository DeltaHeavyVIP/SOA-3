package com.example.additional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdditionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdditionalApplication.class, args);
	}

}
