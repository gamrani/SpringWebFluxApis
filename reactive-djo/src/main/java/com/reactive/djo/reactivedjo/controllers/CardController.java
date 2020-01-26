package com.reactive.djo.reactivedjo.controllers;

import com.reactive.djo.reactivedjo.models.Card;
import com.reactive.djo.reactivedjo.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/")
    public Flux<Card> findAllCards(){
        return cardService.getAllCards();
    }

}
