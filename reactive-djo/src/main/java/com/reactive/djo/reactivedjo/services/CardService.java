package com.reactive.djo.reactivedjo.services;

import com.reactive.djo.reactivedjo.models.Account;
import   com.reactive.djo.reactivedjo.models.Card;
import com.reactive.djo.reactivedjo.models.CardEvent;
import com.reactive.djo.reactivedjo.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CardService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private CardRepository cardRepository;
    @Value("${card.api.url}")
    private String cardApiUrl;

    public Mono<Card> activateCard(String cardId,int accountNumber) {
        Mono<Card> card = webClient.get()
                .uri(cardApiUrl+"/accountNumber/{accountNumber}",accountNumber)
                .retrieve()
                .bodyToMono(Account.class)
                .flatMap(account -> {
                    System.out.println("****ACCOUNT FOUND: "+account);
                    return addAccountToCard(cardId, account);
                });

        return card;
    }

    public Flux<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public Mono<Card> getCardById(String id) {
        return cardRepository.findById(id);
    }

    public Mono<Card> save(Card card) {
        return cardRepository.save(card);
    }

    public Mono<Card> updateCard(String id, Card card) {
        return cardRepository.findById(id)
                .flatMap(existingCard -> {
                    existingCard.setName(card.getName());
                    existingCard.setType(card.getType());
                    return cardRepository.save(existingCard);
                });
    }

    public Mono<Card> addAccountToCard(String id, Account account){
        return cardRepository.findById(id)
                .flatMap(existingCard -> {
                    existingCard.setAccount(account);
                    return cardRepository.save(existingCard);
                });
    }

    public Mono<Void> deleteCard(String id) {
        return cardRepository.findById(id)
                .flatMap(cardRepository::delete);
    }

    public Mono<Void> deleteAll() {
        return cardRepository.deleteAll();
    }

    public Flux<CardEvent> getCardEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(value -> new CardEvent(value, "Product Event"));
    }


}
