package com.MindHub.homebanking.services;

import com.MindHub.homebanking.dtos.ClientDTO;
import com.MindHub.homebanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsDTO();

    Client getClientCurrent(Authentication authentication);

    ClientDTO getClientDTO(long id);

    void saveClient(Client client);

    Client getClientByEmail(String email);
}
