package org.example.proxibanque.service.helper.impl;

import org.example.proxibanque.model.entity.Agency;
import org.example.proxibanque.model.repository.AgencyRepository;
import org.example.proxibanque.service.helper.AgencyServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgencyServiceHelperImpl implements AgencyServiceHelper {

    private AgencyRepository agencyRepository;

    @Autowired
    public AgencyServiceHelperImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public Optional<Agency> getAgency(String id) {
        return agencyRepository.findById(id);
    }
}
