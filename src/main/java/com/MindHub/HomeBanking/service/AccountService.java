package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.dto.TransactionDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAllAccountDTO();

    List<Account> getAllAccounts();

    List<TransactionDTO> getAccountTransactionsDTO(Long id);

    Account findByNumber (String number);

    boolean existsByNumber (String number);

    void saveAccount (Account account);
}
