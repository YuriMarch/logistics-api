package com.example.logisticsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID incidentId;

    @ManyToOne
    private Delivery delivery;

    private String description;
    private OffsetDateTime registrationDate;
}
