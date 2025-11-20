package org.example.proxibanque.service;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.model.entity.Client;

public interface ClientService {

    Client createClient(ClientCreateRequest request);

}
