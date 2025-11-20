package org.example.proxibanque.service.impl;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.request.CreateRunningAccountRequest;
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
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AgencyServiceHelper agencyServiceHelper) {
        this.clientRepository = clientRepository;
        this.agencyServiceHelper = agencyServiceHelper;
    }

    @Override
    public Client createClient(ClientCreateRequest request) {
        Optional<Agency> agencyOpt = agencyServiceHelper.getAgency(request.getAgencyId());
        // Un systeme de globalHandler pour les exceptions seraient necessaires mais pas le temps
        if (agencyOpt.isEmpty()) {
            return null;
        }
        Agency agency = agencyOpt.get();
        Client client = new Client(request.getNom(), request.getPrenom(), request.getAdresse(),  request.getCodePostal(), request.getVille(), request.getTelephone(), agency);
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        return  clientRepository.findAll();
    }

    @Override
    public Client openAccount(Long clientId, CreateRunningAccountRequest accountDto) {
        Client client = clientRepository.getClientById(clientId);
        RunningAccount runningAccount = new RunningAccount(accountDto.getInitialAmount());
        client.setRunningAccount(runningAccount);
        clientRepository.save(client);
        return client;
    }
}
