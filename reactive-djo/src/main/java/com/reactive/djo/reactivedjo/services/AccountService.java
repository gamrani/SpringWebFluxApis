package com.reactive.djo.reactivedjo.services;

import com.reactive.djo.reactivedjo.models.Account;
import com.reactive.djo.reactivedjo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Flux<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Mono<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Mono<Account> save(Account account) {
        return accountRepository.save(account);
    }

    public Mono<Account> updateAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .flatMap(existingAccount -> {
                    existingAccount.setAccountNumber(account.getAccountNumber());
                    return accountRepository.save(existingAccount);
                });
    }

    public Mono<Void> deleteAccount(Long id) {
        return accountRepository.findById(id)
                .flatMap(accountRepository::delete);
    }

    public Mono<Void> deleteAll() {
        return accountRepository.deleteAll();
    }


    public Mono<Account> getAccountByAccountNumber(int accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }
}
