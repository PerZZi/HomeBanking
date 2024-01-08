package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import com.MindHub.HomeBanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepositories clientRepositories;


    @Override
    public List<ClientDTO> getAllClientDTO() {
        return getAllClients().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepositories.findAll();
    }

    @Override
    public Client getAuthenticatedClient(String email) {
        return clientRepositories.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientRepositories.existsByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
       clientRepositories.save(client);
    }
}
