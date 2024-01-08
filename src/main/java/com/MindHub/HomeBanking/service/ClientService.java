package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dto.ClientDTO;
import com.MindHub.HomeBanking.models.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClientDTO();

    List<Client> getAllClients();

    Client getAuthenticatedClient(String email);

    boolean existsByEmail(String email);

    void saveClient(Client client);
}
