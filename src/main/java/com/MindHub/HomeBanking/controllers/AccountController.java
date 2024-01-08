package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.dto.TransactionDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.AccountRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccountDTO();
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionDTO> getAccountTransactions(@PathVariable Long id) {
        return accountService.getAccountTransactionsDTO(id);
    }

    @PostMapping("clients/current/accounts")
    public ResponseEntity<String> createAccount(Authentication authentication){

        Client client = clientService.getAuthenticatedClient(authentication.getName());

        if(client.getAcounts().size() >= 3){
            return new ResponseEntity<>("Se alcanzo limite de cuentas creadas se permiten hasta 3 cuentas", HttpStatus.FORBIDDEN);
        }

        String number;
        do{
            number = "VIN" + getAccountNumber(00000000,99999999);
        }while(accountService.existsByNumber(number));

        Account account = new Account(number,LocalDate.now(),0);
        client.addAcount(account);
        accountService.saveAccount(account);

        return new ResponseEntity<>("Nueva cuenta creada", HttpStatus.CREATED);
    }

    public int getAccountNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
