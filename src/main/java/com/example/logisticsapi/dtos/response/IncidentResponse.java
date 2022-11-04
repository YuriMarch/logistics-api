package com.example.logisticsapi.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
public class IncidentResponse {

    private UUID incidentId;
    private String description;
    private OffsetDateTime registrationDate;
}
