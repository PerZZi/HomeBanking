package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.dto.CreateClient;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping ("")
    public List<ClientDTO> getAllClients(){
      return clientService.getAllClientDTO();
    }

    @PostMapping("")
    public ResponseEntity<String> createClient(
            @RequestBody CreateClient createClient){

        if(createClient.getName().isBlank()){
            return new ResponseEntity<>("El nombre no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(createClient.getLastName().isBlank()){
            return new ResponseEntity<>("El apellido no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(createClient.getEmail().isBlank()){
            return new ResponseEntity<>("El mail no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(createClient.getPassword().isBlank()){
            return new ResponseEntity<>("la contraseña no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if (clientService.existsByEmail(createClient.getEmail())){
            return new ResponseEntity<>("this email is used", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(createClient.getName(), createClient.getName(), createClient.getEmail(), passwordEncoder.encode(createClient.getPassword()));
        clientService.saveClient(client);

        String number;
        do{
            number = "VIN" + getAccountNumber(00000000,99999999);
        }while(accountService.existsByNumber(number));

        Account account = new Account(number, LocalDate.now(),0);
        client.addAcount(account);
        accountService.saveAccount(account);

        return new ResponseEntity<>("Te has registrado", HttpStatus.CREATED);
    }

    @GetMapping("/current")
    public ResponseEntity<Object> getOneClient(Authentication authentication){

        Client client = clientService.getAuthenticatedClient(authentication.getName());
            ClientDTO clientDTO = new ClientDTO(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);


    }

    public int getAccountNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
