package com.reactive.djo.reactivedjo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "cards")
public class Card implements Serializable {
    @Id
    private String id;

    private String name;

    private String type;

    private Account account;

    public Card(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Card() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(getId(), card.getId()) &&
                Objects.equals(getName(), card.getName()) &&
                Objects.equals(getType(), card.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
