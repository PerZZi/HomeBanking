package com.MindHub.HomeBanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {
@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double MaxAmount;
    private double percentage;
    @ElementCollection
    private List<Integer>payments = new ArrayList<>();

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    public Loan() {
    }

    public Loan(String name, double maxAmount,double percentage, List<Integer> payments) {
        this.name = name;
        this.MaxAmount = maxAmount;
        this.percentage = percentage;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        MaxAmount = maxAmount;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoan(this);
        this.clientLoans.add(clientLoan);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", MaxAmount=" + MaxAmount +
                ", percentage=" + percentage +
                ", payments=" + payments +
                ", clientLoans=" + clientLoans +
                '}';
    }
}
