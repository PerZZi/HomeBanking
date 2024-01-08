package com.MindHub.HomeBanking.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class CreateTransaction {

    private double amount;
    private String description;
    private String accountOrigin;
    private String accountDestination;

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getAccountOrigin() {
        return accountOrigin;
    }

    public String getAccountDestination() {
        return accountDestination;
    }
}
