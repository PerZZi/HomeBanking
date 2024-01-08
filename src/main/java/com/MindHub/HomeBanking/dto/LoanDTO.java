package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Loan;

import java.util.List;

public class LoanDTO {

    private Long id;
    private String name;
    private double MaxAmount;
    private List<Integer> payments;

    public LoanDTO(Loan loan) {
        id = loan.getId();
        name = loan.getName();
        MaxAmount = loan.getMaxAmount();
        payments = loan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return MaxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
