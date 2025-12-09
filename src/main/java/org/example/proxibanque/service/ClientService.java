package org.example.proxibanque.service;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.request.CreateRunningAccountRequest;
import org.example.proxibanque.dto.request.TransferRequest;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientResponse createClient(ClientCreateRequest request);

    List<ClientResponse> getAllClients();

    void deleteClient(Long id);

    ClientResponse getClientById(Long id);

    ClientResponse openAccount(Long clientId, CreateRunningAccountRequest accountDto);

    void transferMoney(Long clientId, Long targetId, TransferRequest transferRequest);

    ClientResponse deposit(Long clientId, TransferRequest transferRequest);

    ClientResponse withdraw(Long clientId, TransferRequest transferRequest);

}
