package org.example.proxibanque.mapper;

import org.example.proxibanque.dto.response.AgencyResponse;
import org.example.proxibanque.model.entity.Agency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AgencyMapper {


    AgencyResponse toResponseDto(Agency agency);

}
