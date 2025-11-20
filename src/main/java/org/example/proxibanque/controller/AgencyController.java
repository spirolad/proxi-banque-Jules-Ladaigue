package org.example.proxibanque.controller;

import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AgencyController {

    private AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @PostMapping("agencies")

    public ResponseEntity<Agency> createAgency(@RequestBody AgencyCreateRequest agencyDto) {
        Agency agency = agencyService.createAgency(agencyDto);
        if  (agency == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(agency);
    }

    @GetMapping("agencies")
    public ResponseEntity<List<Agency>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllAgencies());
    }

    @GetMapping("agencies/{id}")
    public ResponseEntity<Agency> getAgency(@PathVariable String id) {
        Optional<Agency> agencyOptional = agencyService.getAgency(id);
        return agencyOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
