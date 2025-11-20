package org.example.proxibanque.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateRequest {

    private String nom;
    private String prenom;
    private String adresse;
    private Integer codePostal;
    private String ville;
    private String telephone;
    private String agencyId;

}
