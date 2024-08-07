package com.lisakzbigniew.flashcards.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(com.lisakzbigniew.flashcards.ServiceConfiguration.class)
public class FlashcardsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApiApplication.class, args);
	}

}
