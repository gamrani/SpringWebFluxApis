package com.reactive.djo.reactivedjo.config;

import com.reactive.djo.reactivedjo.model.Card;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;
import com.reactive.djo.reactivedjo.repositories.CardRepository;
import reactor.core.publisher.Mono;

@Configuration
public class DataGenerator {

    @Bean
    CommandLineRunner init(ReactiveMongoOperations reactiveMongoOperations, CardRepository cardRepository){
        return args -> {
            Flux<Card> cardFlux = Flux.just(
                  new Card(null,"Card 1","Rewards"),
                    new Card(null,"Card 2","Student"),
                    new Card(null,"Card 3","Charge"),
                    new Card(null,"Card 4","Prepaid"),
                    new Card(null,"Card 5","Business")
            ).flatMap(cardRepository::save);

            cardFlux
                    .thenMany(cardRepository.findAll())
                    .subscribe(System.out::println);

            reactiveMongoOperations.collectionExists(Card.class)
                    .flatMapMany(exists -> exists ? reactiveMongoOperations.dropCollection(Card.class): Mono.just(exists))
                    .thenMany(collection -> reactiveMongoOperations.createCollection(Card.class))
                    .thenMany(cardFlux)
                    .thenMany(cardRepository.findAll())
                    .subscribe(System.out::println);
        };
    }
}
