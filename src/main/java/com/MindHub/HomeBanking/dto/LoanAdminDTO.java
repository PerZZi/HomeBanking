package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Loan;

import java.util.List;

public class LoanAdminDTO {

    private String name;
    private double MaxAmount;
    private double percentage;
    private List<Integer> payments;

    public LoanAdminDTO(Loan loan) {
        name = loan.getName();
        MaxAmount = loan.getMaxAmount();
        percentage = loan.getPercentage();
        payments = loan.getPayments();
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return MaxAmount;
    }

    public double getPercentage() {
        return percentage;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
