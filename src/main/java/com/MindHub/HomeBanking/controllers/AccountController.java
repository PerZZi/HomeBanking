package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.AccountDTO;
import com.MindHub.HomeBanking.dto.TransactionDTO;
import com.MindHub.HomeBanking.dto.TypeAccount;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


import static com.MindHub.HomeBanking.utils.Utils.numberAccount;

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
    public ResponseEntity<String> createAccount(Authentication authentication,
                                                @RequestParam TypeAccount typeAccount){

        Client client = clientService.getAuthenticatedClient(authentication.getName());

        if(client.getAcounts().size() >= 3){
            return new ResponseEntity<>("Se alcanzo limite de cuentas creadas se permiten hasta 3 cuentas", HttpStatus.FORBIDDEN);
        }

        String number;
        do{
            number = numberAccount();
        }while(accountService.existsByNumber(number));

        Account account = new Account(number,LocalDate.now(),0,true, typeAccount);
        client.addAccount(account);
        accountService.saveAccount(account);

        return new ResponseEntity<>("Nueva cuenta creada", HttpStatus.CREATED);
    }

    @PatchMapping("clients/current/accounts/delete")
    public ResponseEntity<String> deleteAccount(
            @RequestParam String number,
            Authentication authentication){

        Client client = clientService.getAuthenticatedClient(authentication.getName());
        Account account = accountService.findByNumber(number);

        if(account.isStateAccount() && account.getClient().getEmail().equals(authentication.getName())){
            if(account.getBalance() == 0){
                accountService.deleteAccount(account);
                return new ResponseEntity<>("Cuenta eliminada", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No se puede eliminar la cuenta tiene saldo", HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>("La cuenta ya fue eliminada", HttpStatus.BAD_REQUEST);
    }

}
