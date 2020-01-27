package com.reactive.djo.reactivedjo.repositories;

import com.reactive.djo.reactivedjo.models.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository
        extends ReactiveMongoRepository<Card, String> {
}
