package com.reactive.djo.reactivedjo.controllers;

import com.reactive.djo.reactivedjo.models.Card;
import com.reactive.djo.reactivedjo.models.CardEvent;
import com.reactive.djo.reactivedjo.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/{cardId}/account/{accountNumber}")
    public Mono<ResponseEntity<Card>> activateCard(@PathVariable int accountNumber, @PathVariable String cardId){
        return cardService.activateCard(cardId,accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping
    public Flux<Card> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Card>> getCardById(@PathVariable String id){
        return cardService.getCardById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Card> saveCard(@RequestBody Card card){
         return cardService.save(card);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Card>> updateCard(@PathVariable(value="id") String  id,
                                                 @RequestBody Card card){
        return cardService.updateCard(id, card)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCard(@PathVariable String id){
        return cardService.deleteCard(id)
               .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllCards(){
        return cardService.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CardEvent> getCardEvents(){
        return cardService.getCardEvents();
    }

}
