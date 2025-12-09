package org.example.proxibanque.mapper;

import org.example.proxibanque.dto.response.ClientResponse;
import org.example.proxibanque.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMapper {

    @Mapping(target = "runningAccountAmount", source = "client.runningAccount.solde")
    @Mapping(target = "agency", source = "client.agency.id")
    ClientResponse clientToDto(Client client);

}
