package com.reactive.djo.reactivedjo.repositories;

import com.reactive.djo.reactivedjo.model.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CardRepository
        extends ReactiveMongoRepository<Card, String> {
}
