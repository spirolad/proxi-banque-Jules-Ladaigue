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
        return ResponseEntity.ok(agencyService.createAgency(agencyDto));
    }

    @GetMapping("agencies")
    public ResponseEntity<List<AgencyResponse>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllAgencies());
    }

    @GetMapping("agencies/{id}")
    public ResponseEntity<AgencyResponse> getAgency(@PathVariable String id) {
        return ResponseEntity.ok(agencyService.getAgency(id));
    }

    @GetMapping("agencies/{id}/clients")
    public ResponseEntity<Set<ClientResponse>> getClients(@PathVariable String id) {
        return ResponseEntity.ok(agencyService.getAllClients(id));
    }

}
