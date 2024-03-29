package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Transaction;
import com.MindHub.HomeBanking.models.TransactionType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;
    private double balanceFinal;


    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        date = transaction.getDate();
        balanceFinal = transaction.getBalanceFinal();
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getBalanceFinal() {
        return balanceFinal;
    }
}
