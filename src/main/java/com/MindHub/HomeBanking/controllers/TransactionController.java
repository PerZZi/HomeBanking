package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.dto.CreateTransaction;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.Transaction;
import com.MindHub.HomeBanking.models.TransactionType;
import com.MindHub.HomeBanking.repositories.AccountRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import com.MindHub.HomeBanking.repositories.TransactionRepositories;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.ClientService;
import com.MindHub.HomeBanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;


    @PostMapping("/transactions")
    @Transactional
    public ResponseEntity<String> createTransactions(
            @RequestBody CreateTransaction createTransaction,
            Authentication authentication){

        Account originAccount = accountService.findByNumber(createTransaction.getAccountOrigin());
        Account destinationAccount = accountService.findByNumber(createTransaction.getAccountDestination());

        if(createTransaction.getAmount() <= 0){
            return new ResponseEntity<>("El monto no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(createTransaction.getDescription().isBlank()){
            return new ResponseEntity<>("La descripcion no puede estar vacia", HttpStatus.FORBIDDEN);
        }
        if(createTransaction.getAccountOrigin().isBlank()){
            return new ResponseEntity<>("El numero de cuenta no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(createTransaction.getAccountDestination().isBlank()){
            return new ResponseEntity<>("El numero de cuenta destino no puede estar vacio", HttpStatus.FORBIDDEN);
        }

        if (originAccount.getBalance() < createTransaction.getAmount()) {
            return new ResponseEntity<>("Fondos insuficientes en la cuenta de origen", HttpStatus.FORBIDDEN);
        }

        if(createTransaction.getAccountOrigin().equals(createTransaction.getAccountDestination())){
            return new ResponseEntity<>("No se puede transferir a una misma cuenta", HttpStatus.FORBIDDEN);
        }

        if (!originAccount.getClient().getEmail().equals(authentication.getName())) {
            return new ResponseEntity<>("La cuenta de origen no pertenece al cliente autenticado", HttpStatus.FORBIDDEN);
        }

        Transaction transactionDebit = new Transaction(TransactionType.DEBIT, createTransaction.getAmount() , createTransaction.getDescription() , LocalDateTime.now(),0);
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, createTransaction.getAmount() , createTransaction.getDescription() , LocalDateTime.now(),0);

        transactionDebit.setBalanceFinal(originAccount.getBalance() - createTransaction.getAmount());
        transactionCredit.setBalanceFinal(destinationAccount.getBalance() + createTransaction.getAmount());

        originAccount.addTransaction(transactionDebit);
        destinationAccount.addTransaction(transactionCredit);
        transactionService.saveTransaction(transactionCredit);
        transactionService.saveTransaction(transactionDebit);
        originAccount.setBalance(originAccount.getBalance() - createTransaction.getAmount());
        destinationAccount.setBalance(destinationAccount.getBalance() + createTransaction.getAmount());
        accountService.saveAccount(originAccount);
        accountService.saveAccount(destinationAccount);

        return new ResponseEntity<>("Nueva transaccion con exito", HttpStatus.CREATED);
    }
}
