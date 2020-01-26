package com.reactive.djo.reactivedjo.services;

import com.reactive.djo.reactivedjo.models.Card;
import com.reactive.djo.reactivedjo.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Flux<Card> getAllCards(){
        return cardRepository.findAll();
    }
}
