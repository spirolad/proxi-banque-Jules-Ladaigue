package org.example.proxibanque.service.impl;

import jakarta.annotation.PostConstruct;
import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.dto.response.AgencyResponse;
import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.exception.custom.AgencyNotFoundException;
import org.example.proxibanque.exception.custom.InvalidDataException;
import org.example.proxibanque.mapper.AgencyMapper;
import org.example.proxibanque.mapper.ClientMapper;
import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.model.entity.Client;
import org.example.proxibanque.model.repository.AgencyRepository;
import org.example.proxibanque.service.AgencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;
    private final ClientMapper clientMapper;

    public AgencyServiceImpl(AgencyRepository agencyRepository, AgencyMapper agencyMapper, ClientMapper clientMapper) {
        this.agencyRepository = agencyRepository;
        this.agencyMapper = agencyMapper;
        this.clientMapper = clientMapper;
    }

    @PostConstruct
    public void init() {
        agencyRepository.deleteAll();
        agencyRepository.save(new Agency("strasbourg"));
        agencyRepository.save(new Agency("nancy"));
    }

    @Override
    public AgencyResponse createAgency(AgencyCreateRequest agencyDto) {
        if (agencyDto == null || agencyDto.id() == null) {
            throw new InvalidDataException();
        }
        Agency agency = new Agency(agencyDto.id());
        agencyRepository.save(agency);
        return agencyMapper.toResponseDto(agency);
    }

    @Override
    public List<AgencyResponse> getAllAgencies() {
        return  agencyRepository.findAll().stream().map(agencyMapper::toResponseDto).toList();
    }

    @Override
    public AgencyResponse getAgency(String id) {
        return agencyRepository.findById(id).map(agencyMapper::toResponseDto).orElseThrow(AgencyNotFoundException::new);
    }

    @Override
    public Set<ClientResponse> getAllClients(String id) {
        Agency agency = agencyRepository.findById(id).orElseThrow(AgencyNotFoundException::new);
        return agency.getClients().stream().map(clientMapper::clientToDto).collect(Collectors.toSet());
    }
}
