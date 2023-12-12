package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.dto.TransactionDTO;
import com.MindHub.HomeBanking.repositories.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepositories accountRepositories;

    @GetMapping("/accounts")
    public List<AccountDTO> getAllAccounts(){
        return accountRepositories.findAll()
                .stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountRepositories.findById(id).map(AccountDTO::new).orElse(null);
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionDTO> getAccountTransactions(@PathVariable Long id) {
        return accountRepositories.findById(id)
                .map(account -> account.getTransactions().stream()
                        .map(TransactionDTO::new)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
