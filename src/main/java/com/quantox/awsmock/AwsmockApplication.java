package com.quantox.awsmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AwsmockApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsmockApplication.class, args);
	}

}
