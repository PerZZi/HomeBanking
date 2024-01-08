package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.dto.TransactionDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.repositories.AccountRepositories;
import com.MindHub.HomeBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountRepositories accountRepositories;


    @Override
    public List<AccountDTO> getAllAccountDTO() {
        return getAllAccounts().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepositories.findAll();
    }

    @Override
    public List<TransactionDTO> getAccountTransactionsDTO(Long id) {
        return accountRepositories.findById(id).map(account -> account.getTransactions()
                .stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepositories.findByNumber(number);
    }

    @Override
    public boolean existsByNumber(String number) {
        return accountRepositories.existsByNumber(number);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepositories.save(account);
    }
}
