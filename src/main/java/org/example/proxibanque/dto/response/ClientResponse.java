package org.example.proxibanque.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private String nom;
    private String prenom;
    private String adresse;
    private Integer codePostal;
    private String ville;
    private String telephone;
    private String agency;

}
