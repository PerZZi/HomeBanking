package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.ClienteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClienteRepositories clienteRepositories;

    @RequestMapping ("/all")
    public List<ClientDTO> getAllClient(){
      return clienteRepositories.findAll()
              .stream()

              .map(ClientDTO::new)
              .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id){
      return clienteRepositories.findById(id).map(ClientDTO::new).orElse(null);
    }

  @PostMapping
    public void petitionPost(){
      System.out.println("Recibi un post");
  }

  @DeleteMapping
    public void petitionDelete(){
      System.out.println("Recibi un delete");
  }
}
