package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.Transaction;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {

    private Long id;
    private String number;
    private LocalDate creationDate;
    private double balance;
    private boolean StateAccount;
    private TypeAccount typeAccount;
    private List<TransactionDTO> transactions;


    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
        StateAccount = account.isStateAccount();
        typeAccount = account.getTypeAccount();
        transactions = account.getTransactions().stream()
                .filter(accountDTO -> account.isStateAccount())
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
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

    public boolean isStateAccount() {
        return StateAccount;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }
}
