package org.example.proxibanque.controller;

import org.example.proxibanque.dto.request.ClientCreateRequest;
import org.example.proxibanque.dto.request.CreateRunningAccountRequest;
import org.example.proxibanque.dto.request.TransferRequest;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.mapper.ClientMapper;
import org.example.proxibanque.model.entity.Client;
import org.example.proxibanque.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("clients")
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientCreateRequest clientDto) {
        return ResponseEntity.ok(clientService.createClient(clientDto));
    }

    @GetMapping("clients")
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity<ClientResponse> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping("clients/{id}/runningAccount")
    public ResponseEntity<ClientResponse> openRunningAccount(@PathVariable Long id, @RequestBody CreateRunningAccountRequest clientDto) {
        return ResponseEntity.ok(clientService.openAccount(id, clientDto));
    }


    @PostMapping("clients/{id}/deposit")
    public ResponseEntity<ClientResponse> deposit(@PathVariable Long id, @RequestBody TransferRequest transferDto) {
        return ResponseEntity.ok(clientService.deposit(id, transferDto));
    }

    @PostMapping("clients/{id}/withdraw")
    public ResponseEntity<ClientResponse> withdraw(@PathVariable Long id, @RequestBody TransferRequest transferDto) {
        return ResponseEntity.ok(clientService.withdraw(id, transferDto));
    }

    @PostMapping("clients/{id}/transfer/{target}")
    public ResponseEntity<ClientResponse> transferMoney(@PathVariable Long id, @PathVariable Long target, @RequestBody TransferRequest transferRequest) {
        clientService.transferMoney(id, target, transferRequest);
        return ResponseEntity.noContent().build();

    }

}
