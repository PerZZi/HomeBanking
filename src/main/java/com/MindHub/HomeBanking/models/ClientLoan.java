package com.MindHub.HomeBanking.models;

import jakarta.persistence.*;

@Entity
public class ClientLoan {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@ManyToOne
    private Loan loan;
    private double amount;
    private Integer payments;
    @ManyToOne
    private Client client;

    public ClientLoan() {
    }

    public ClientLoan(double amount, Integer payments) {
        this.amount = amount;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientLoan{" +
                "id=" + id +
                ", loan=" + loan +
                ", amount=" + amount +
                ", payments=" + payments +
                ", client=" + client +
                '}';
    }
}
