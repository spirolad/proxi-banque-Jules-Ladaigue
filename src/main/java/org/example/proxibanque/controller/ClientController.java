package org.example.proxibanque.controller;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.model.entity.Client;
import org.example.proxibanque.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("clients")
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientCreateRequest clientDto) {
        Client client = clientService.createClient(clientDto);
        if (client == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clientToDto(client));
    }


    private ClientResponse clientToDto(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setAdresse(client.getAdresse());
        clientResponse.setNom(client.getNom());
        clientResponse.setPrenom(client.getPrenom());
        clientResponse.setTelephone(client.getTelephone());
        clientResponse.setCodePostal(client.getCodePostal());
        clientResponse.setAgency(client.getAgency().getId());
        clientResponse.setVille(client.getVille());
        return clientResponse;
    }

}
