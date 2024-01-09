package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Client;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long ID;
    private String name;
    private String lastName;
    private String email;
    private List<AccountDTO> accounts;
    private Set<ClientLoanDTO> clientLoans;
    private Set<CardDTO> cards;

    public ClientDTO(Client client) {
        ID = client.getId();
        name = client.getName();
        lastName = client.getLastName();
        email = client.getEmail();
        accounts = client.getAcounts()
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
        clientLoans = client.getClientLoans()
                .stream()
                .map(clientLoan -> new ClientLoanDTO(clientLoan))
                .collect(Collectors.toSet());
        cards = client.getCards()
                .stream()
                .map(card -> new CardDTO(card))
                .collect(Collectors.toSet());
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<ClientLoanDTO> getClientLoans() {
        return clientLoans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }
}
