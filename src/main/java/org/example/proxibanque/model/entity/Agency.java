package org.example.proxibanque.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Agency {

    @Id
    private String id;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Agency(String id) {
        this.id = id;
    }

}
