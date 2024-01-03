package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.Transaction;
import com.MindHub.HomeBanking.models.TransactionType;
import com.MindHub.HomeBanking.repositories.AccountRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import com.MindHub.HomeBanking.repositories.TransactionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Transactional
public class TransactionController {

    @Autowired
    private TransactionRepositories transactionRepositories;

    @Autowired
    private ClientRepositories clientRepositories;

    @Autowired
    private AccountRepositories accountRepositories;


    @PostMapping("/transactions")
    public ResponseEntity<String> createTransactions(
            @RequestParam double amount,
            @RequestParam String description,
            @RequestParam String accountOrigin,
            @RequestParam String accountDestination,
            Authentication authentication){

        Account originAccount = accountRepositories.findByNumber(accountOrigin);
        Account destinationAccount = accountRepositories.findByNumber(accountDestination);

        if(amount <= 0){
            return new ResponseEntity<>("El monto no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(description.isBlank()){
            return new ResponseEntity<>("La descripcion no puede estar vacia", HttpStatus.FORBIDDEN);
        }
        if(accountOrigin.isBlank()){
            return new ResponseEntity<>("El numero de cuenta no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(accountDestination.isBlank()){
            return new ResponseEntity<>("El numero de cuenta destino no puede estar vacio", HttpStatus.FORBIDDEN);
        }

        if (originAccount.getBalance() < amount) {
            return new ResponseEntity<>("Fondos insuficientes en la cuenta de origen", HttpStatus.FORBIDDEN);
        }

        if(accountOrigin.equals(accountDestination)){
            return new ResponseEntity<>("No se puede transferir a una misma cuenta", HttpStatus.FORBIDDEN);
        }

        if (!originAccount.getClient().getEmail().equals(authentication.getName())) {
            return new ResponseEntity<>("La cuenta de origen no pertenece al cliente autenticado", HttpStatus.FORBIDDEN);
        }

        Transaction transactionDebit = new Transaction(TransactionType.DEBIT, amount , description , LocalDateTime.now());
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT, amount , description , LocalDateTime.now());
        originAccount.addTransaction(transactionDebit);
        destinationAccount.addTransaction(transactionCredit);
        transactionRepositories.save(transactionCredit);
        transactionRepositories.save(transactionDebit);
        originAccount.setBalance(originAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepositories.save(originAccount);
        accountRepositories.save(destinationAccount);

        return new ResponseEntity<>("Nueva transaccion con exito", HttpStatus.CREATED);
    }
}
