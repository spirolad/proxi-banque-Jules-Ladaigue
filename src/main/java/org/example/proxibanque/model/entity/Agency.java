package org.example.proxibanque.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Agency {

    @Id
    private String id;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    @OneToMany(mappedBy = "agency")
    private Set<Client> clients;

    public Agency(String id) {
        this.id = id;
    }

}
