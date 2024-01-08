package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.LoanApplicationDTO;
import com.MindHub.HomeBanking.dto.LoanDTO;
import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.ClientLoanRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import com.MindHub.HomeBanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientLoanService clientLoanService;

    @GetMapping("/loans")
    public List<LoanDTO> getAllLoan() {
        return loanService.getAllLoans();
    }

    @PostMapping("/loans")
    @Transactional
    public ResponseEntity<Object> createLoans(
            @RequestBody LoanApplicationDTO loanApplicationDTO,
            Authentication authentication){

        Client client = clientService.getAuthenticatedClient(authentication.getName());
        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount()*1.2, loanApplicationDTO.getPayments());
        Account accountDestination = accountService.findByNumber(loanApplicationDTO.getAccountDestination());
        Loan loan = loanService.findById(loanApplicationDTO.getId());

        if(loanApplicationDTO.getAmount() <= 0){
            return new ResponseEntity<>("El monto no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(loanApplicationDTO.getPayments() <= 0){
            return new ResponseEntity<>("Las cuotas no pueden estar vacias", HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getAccountDestination() == null){
            return new ResponseEntity<>("La cuenta destino no puede estar vacia", HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getAmount() > loanService.findById(loanApplicationDTO.getId()).getMaxAmount()){
            return new ResponseEntity<>("El monto de la cuota no puede ser mayor al del prestamo", HttpStatus.FORBIDDEN);
        }

        if(!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("No existe esa cantidad de cuotas para pagar", HttpStatus.FORBIDDEN);
        }

        LoanDTO loanDTO = loanService.findByIdLoanDTO(loanApplicationDTO.getId());
        client.addClientLoan(clientLoan);
        loan.addClientLoan(clientLoan);
        clientLoanService.saveClientLoan(clientLoan);

        Transaction transaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + "prestamo aprobado", LocalDateTime.now());
        accountDestination.setBalance(accountDestination.getBalance()+ loanApplicationDTO.getAmount());
        accountService.saveAccount(accountDestination);

        return new ResponseEntity<>("Nuevo prestamo creado", HttpStatus.CREATED);
    }

}
