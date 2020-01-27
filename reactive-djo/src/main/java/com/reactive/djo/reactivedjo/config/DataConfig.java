package com.reactive.djo.reactivedjo.config;


import com.reactive.djo.reactivedjo.models.Account;
import com.reactive.djo.reactivedjo.models.Card;
import com.reactive.djo.reactivedjo.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;
import com.reactive.djo.reactivedjo.repositories.CardRepository;
import reactor.core.publisher.Mono;

@Configuration
public class DataConfig{

    CommandLineRunner initAccounts(ReactiveMongoOperations reactiveMongoOperations, AccountRepository accountRepository){
        return args -> {
          Flux<Account> accountFlux = Flux.just(
             new Account(null,1),
                  new Account(null,2),
                  new Account(null,3),
                  new Account(null,4)
          ).flatMap(accountRepository::save) ;

          accountFlux
                  .thenMany(accountRepository.findAll())
                  .subscribe(System.out::println);

            reactiveMongoOperations.collectionExists(Account.class)
                    .flatMapMany(exists -> exists ? reactiveMongoOperations.dropCollection(Account.class): Mono.just(exists))
                    .thenMany(collection -> reactiveMongoOperations.createCollection(Account.class))
                    .thenMany(accountFlux)
                    .thenMany(accountRepository.findAll())
                    .subscribe(System.out::println);
        };
    }

    CommandLineRunner initCards(ReactiveMongoOperations reactiveMongoOperations, CardRepository cardRepository){
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
