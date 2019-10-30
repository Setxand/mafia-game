package com.mafia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@EnableConfigurationProperties
@SpringBootApplication
public class MafiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MafiaApplication.class, args);
	}


	@Bean
	public Random random() {
		return new Random();
	}
}
