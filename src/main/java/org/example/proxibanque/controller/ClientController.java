package org.example.proxibanque.controller;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.request.CreateRunningAccountRequest;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.mapper.ClientMapper;
import org.example.proxibanque.model.entity.Client;
import org.example.proxibanque.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("clients")
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientCreateRequest clientDto) {
        Client client = clientService.createClient(clientDto);
        if (client == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ClientMapper.clientToDto(client));
    }

    @GetMapping("clients")
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clients = clientService.getAllClients().stream().map(ClientMapper::clientToDto).toList();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("clients/{id}/runningAccount")
    public ResponseEntity<ClientResponse> openRunningAccount(@PathVariable Long id, @RequestBody CreateRunningAccountRequest clientDto) {
        Client client = clientService.openAccount(id, clientDto);
        if (client == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ClientMapper.clientToDto(client));
    }

}
