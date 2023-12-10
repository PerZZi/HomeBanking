package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long ID;
    private String name;
    private String lastName;
    private String email;
    private List<AccountDTO> accounts;

    public ClientDTO(Client client) {
        ID = client.getID();
        name = client.getName();
        lastName = client.getLastName();
        email = client.getEmail();
        accounts = client.getAcounts()
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
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
}
