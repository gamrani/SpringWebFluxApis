package com.reactive.djo.reactivedjo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveDjoApplication {
	@Value("${card.api.url}")
	private String cardApiUrl;

	@Bean
	WebClient webClientConfig(){
		return WebClient.create(cardApiUrl);
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveDjoApplication.class, args);
	}

}
