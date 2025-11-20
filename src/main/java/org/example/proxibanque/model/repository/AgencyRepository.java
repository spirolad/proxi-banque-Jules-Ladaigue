package org.example.proxibanque.model.repository;

import org.example.proxibanque.model.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, String> {

}
