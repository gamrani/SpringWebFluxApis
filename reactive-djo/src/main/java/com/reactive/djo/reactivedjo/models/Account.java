package com.reactive.djo.reactivedjo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "accounts")
public class Account implements Serializable {
    @Id
    private String id;
    private int accountNumber;

    public Account(String id, int accountNumber) {
        this.id = id;
        this.accountNumber = accountNumber;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

}
