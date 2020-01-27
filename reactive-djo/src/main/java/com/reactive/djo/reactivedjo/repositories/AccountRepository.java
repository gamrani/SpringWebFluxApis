package com.reactive.djo.reactivedjo.repositories;

import com.reactive.djo.reactivedjo.models.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository
        extends ReactiveMongoRepository<Account, Long> {
     Mono<Account> findAccountByAccountNumber(int accountNumber);
}
