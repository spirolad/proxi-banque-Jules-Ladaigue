package org.example.proxibanque.controller;

import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.dto.response.AgencyResponse;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.mapper.AgencyMapper;
import org.example.proxibanque.mapper.ClientMapper;
import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.model.entity.Client;
import org.example.proxibanque.service.AgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class AgencyController {

    private AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PostMapping("agencies")

    public ResponseEntity<AgencyResponse> createAgency(@RequestBody AgencyCreateRequest agencyDto) {
        Agency agency = agencyService.createAgency(agencyDto);
        if  (agency == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(AgencyMapper.agencyResponse(agency));
    }

    @GetMapping("agencies")
    public ResponseEntity<List<AgencyResponse>> getAllAgencies() {
        List<AgencyResponse> agencyResponseList = agencyService.getAllAgencies().stream().map(AgencyMapper::agencyResponse).toList();
        return ResponseEntity.ok(agencyResponseList);
    }

    @GetMapping("agencies/{id}")
    public ResponseEntity<AgencyResponse> getAgency(@PathVariable String id) {
        Optional<Agency> agencyOptional = agencyService.getAgency(id);
        return agencyOptional.map(agency -> ResponseEntity.ok(AgencyMapper.agencyResponse(agency))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("agencies/{id}/clients")
    public ResponseEntity<List<ClientResponse>> getClients(@PathVariable String id) {
        Set<Client> clients = agencyService.getAllClients(id);
        if (clients == null) {
            return ResponseEntity.notFound().build();
        }
        List<ClientResponse> clientResponseList = clients.stream().map(ClientMapper::clientToDto).toList();
        return ResponseEntity.ok(clientResponseList);
    }

}
