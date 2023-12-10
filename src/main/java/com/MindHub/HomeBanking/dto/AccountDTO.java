package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class AccountDTO {

    private Long id;
    private String number;
    private LocalDate creationDate;
    private double balance;


    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

}
