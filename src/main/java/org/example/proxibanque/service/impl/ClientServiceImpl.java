package org.example.proxibanque.service.impl;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.request.CreateRunningAccountRequest;
import org.example.proxibanque.dto.request.TransferRequest;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.exception.custom.ClientNotFoundException;
import org.example.proxibanque.exception.custom.InvalidDataException;
import org.example.proxibanque.exception.custom.NoAccountException;
import org.example.proxibanque.exception.custom.NotEnoughMoneyException;
import org.example.proxibanque.mapper.ClientMapper;
import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.model.entity.Client;
import org.example.proxibanque.model.entity.RunningAccount;
import org.example.proxibanque.model.repository.ClientRepository;
import org.example.proxibanque.service.ClientService;
import org.example.proxibanque.service.helper.AgencyServiceHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final AgencyServiceHelper agencyServiceHelper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, AgencyServiceHelper agencyServiceHelper, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.agencyServiceHelper = agencyServiceHelper;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponse createClient(ClientCreateRequest request) {
        Optional<Agency> agencyOpt = agencyServiceHelper.getAgency(request.agencyId());
        if (agencyOpt.isEmpty()) {
            throw new InvalidDataException();
        }
        Agency agency = agencyOpt.get();
        Client client = new Client(request.nom(), request.prenom(), request.adresse(),  request.codePostal(), request.ville(), request.telephone(), agency);
        clientRepository.save(client);
        return clientMapper.clientToDto(client);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return  clientRepository.findAll().stream().map(clientMapper::clientToDto).toList();
    }

    @Override
    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientResponse getClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::clientToDto).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public ClientResponse openAccount(Long clientId, CreateRunningAccountRequest accountDto) {
        Client client = clientRepository.getClientById(clientId);
        RunningAccount runningAccount = new RunningAccount(accountDto.initialAmount());
        runningAccount.setClient(client);
        client.setRunningAccount(runningAccount);
        clientRepository.save(client);
        return clientMapper.clientToDto(client);
    }

    @Override
    public void transferMoney(Long clientId, Long targetId, TransferRequest transferRequest) {
        Client client = clientRepository.getClientById(clientId);
        Client targetClient = clientRepository.getClientById(targetId);
        if (client == null || targetClient == null)
            throw new InvalidDataException();
        if (client.getRunningAccount() ==  null || targetClient.getRunningAccount() == null) {
            throw new InvalidDataException();
        }
        if (!client.getRunningAccount().withdraw(transferRequest.amount())) {
            throw new NotEnoughMoneyException();
        }
        targetClient.getRunningAccount().deposit(transferRequest.amount());
        clientRepository.save(targetClient);
        clientRepository.save(client);
    }

    @Override
    public ClientResponse deposit(Long clientId, TransferRequest transferRequest) {
        Client client = clientRepository.getClientById(clientId);
        if  (client == null)
            throw new InvalidDataException();
        RunningAccount runningAccount = client.getRunningAccount();
        if (runningAccount == null)
            throw new NoAccountException();
        runningAccount.deposit(transferRequest.amount());
        clientRepository.save(client);
        return clientMapper.clientToDto(client);
    }

    @Override
    public ClientResponse withdraw(Long clientId, TransferRequest transferRequest) {
        Client client = clientRepository.getClientById(clientId);
        if (client == null)
            throw new InvalidDataException();
        RunningAccount runningAccount = client.getRunningAccount();
        if (runningAccount == null)
            throw new NoAccountException();
        if (!runningAccount.withdraw(transferRequest.amount()))
            throw new NotEnoughMoneyException();
        clientRepository.save(client);
        return clientMapper.clientToDto(client);
    }
}
