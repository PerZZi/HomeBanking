package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.ClientLoan;
import com.MindHub.HomeBanking.models.Loan;
import jakarta.persistence.ManyToOne;

public class ClientLoanDTO {

    private Long id;
    private Long loan;
    private double amount;
    private Integer payments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loan = clientLoan.getLoan().getId();
        amount = clientLoan.getAmount();
        payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public Long getLoan() {
        return loan;
    }

    public double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

}
