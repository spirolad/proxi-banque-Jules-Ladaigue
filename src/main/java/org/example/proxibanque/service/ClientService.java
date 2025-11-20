package org.example.proxibanque.service;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.request.CreateRunningAccountRequest;
import org.example.proxibanque.dto.request.TransferRequest;
import org.example.proxibanque.model.entity.Client;

import java.util.List;

public interface ClientService {

    Client createClient(ClientCreateRequest request);

    List<Client> getAllClients();

    void deleteClient(Long id);

    Client openAccount(Long clientId, CreateRunningAccountRequest accountDto);

    boolean transferMoney(Long clientId, Long targetId, TransferRequest transferRequest);

}
