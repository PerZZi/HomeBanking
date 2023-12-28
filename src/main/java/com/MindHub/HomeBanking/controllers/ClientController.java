package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.AccountRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
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
    private ClientRepositories clientRepositories;

    @Autowired
    private AccountRepositories accountRepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping ("/all")
    public List<ClientDTO> getAllClient(){
      return clientRepositories.findAll()
              .stream()
              .map(ClientDTO::new)
              .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id){
      return clientRepositories.findById(id).map(ClientDTO::new).orElse(null);
    }

    @PostMapping("")
    public ResponseEntity<String> createClient(
             @RequestParam String name,
             @RequestParam String lastName,
             @RequestParam String email,
             @RequestParam String password){

        if(name.isBlank()){
            return new ResponseEntity<>("El nombre no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(lastName.isBlank()){
            return new ResponseEntity<>("El apellido no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(email.isBlank()){
            return new ResponseEntity<>("El mail no puede estar vacio", HttpStatus.FORBIDDEN);
        }
        if(password.isBlank()){
            return new ResponseEntity<>("la contraseña no puede estar vacio", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(name, lastName, email, passwordEncoder.encode(password));
        clientRepositories.save(client);

        String number;
        do{
            number = "VIN" + getAccountNumber(00000000,99999999);
        }while(accountRepositories.existsByNumber(number));

        Account account = new Account(number, LocalDate.now(),0);
        client.addAcount(account);
        accountRepositories.save(account);

        return new ResponseEntity<>("Te has registrado", HttpStatus.CREATED);
    }

    @GetMapping("/current")
    public ResponseEntity<Object> getOneClient(Authentication authentication){

        Client client = clientRepositories.findByEmail(authentication.getName());//devuelve un cliente
            ClientDTO clientDTO = new ClientDTO(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);


    }

    public int getAccountNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
