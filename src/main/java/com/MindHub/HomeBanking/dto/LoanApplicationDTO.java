package com.MindHub.HomeBanking.dto;

public class LoanApplicationDTO {

    private Long id;
    private double amount;
    private Integer payments;
    private String accountDestination;

    public LoanApplicationDTO(Long id, double amount, Integer payments, String accountDestination) {
        this.id = id;
        this.amount = amount;
        this.payments = payments;
        this.accountDestination = accountDestination;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getAccountDestination() {
        return accountDestination;
    }
}
