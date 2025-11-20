package org.example.proxibanque.service.impl;

import jakarta.annotation.PostConstruct;
import org.example.proxibanque.dto.request.AgencyCreateRequest;
import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.model.repository.AgencyRepository;
import org.example.proxibanque.service.AgencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {

    private AgencyRepository agencyRepository;

    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @PostConstruct
    public void init() {
        agencyRepository.deleteAll();
        agencyRepository.save(new Agency("strasbourg"));
        agencyRepository.save(new Agency("nancy"));
    }

    @Override
    public Agency createAgency(AgencyCreateRequest agencyDto) {
        if (agencyDto == null || agencyDto.getId() == null) {
            return null;
        }
        Agency agency = new Agency(agencyDto.getId());
        agencyRepository.save(agency);
        return agency;
    }

    @Override
    public List<Agency> getAllAgencies() {
        return  agencyRepository.findAll();
    }

    @Override
    public Optional<Agency> getAgency(String id) {
        return agencyRepository.findById(id);
    }
}
