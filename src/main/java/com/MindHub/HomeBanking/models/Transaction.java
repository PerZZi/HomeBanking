package com.MindHub.HomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;
    private double balanceFinal;
    @ManyToOne
    private Account account;

    public Transaction() {
    }

    public Transaction(TransactionType type, double amount, String description, LocalDateTime date, double balanceFinal) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.balanceFinal = balanceFinal;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getBalanceFinal() {
        return balanceFinal;
    }

    public void setBalanceFinal(double balanceFinal) {
        this.balanceFinal = balanceFinal;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", balanceFinal=" + balanceFinal +
                '}';
    }
}
