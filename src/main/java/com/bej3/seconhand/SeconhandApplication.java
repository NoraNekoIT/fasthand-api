package com.bej3.seconhand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SeconhandApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeconhandApplication.class, args);
	}

}
