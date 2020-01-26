package com.reactive.djo.reactivedjo.services;

import com.reactive.djo.reactivedjo.models.Card;
import com.reactive.djo.reactivedjo.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

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

    public Mono<Void> deleteCard(String id) {
        return cardRepository.findById(id)
                .flatMap(cardRepository::delete);
    }

    public Mono<Void> deleteAll() {
        return cardRepository.deleteAll();
    }
}
