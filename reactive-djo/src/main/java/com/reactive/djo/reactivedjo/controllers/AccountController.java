package com.reactive.djo.reactivedjo.controllers;

import com.reactive.djo.reactivedjo.models.Account;
import com.reactive.djo.reactivedjo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<Account> getAllaccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Account>> getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public Mono<ResponseEntity<Account>> getAccountByAccountNumber(@PathVariable int accountNumber){
        return accountService.getAccountByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> saveaccount(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Account>> updateAccount(@PathVariable(value="id") Long id,
                                                 @RequestBody Account account){
        return accountService.updateAccount(id, account)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteAccount(@PathVariable Long id){
        return accountService.deleteAccount(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllAccounts(){
        return accountService.deleteAll();
    }

}
