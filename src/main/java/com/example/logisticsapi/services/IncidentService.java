package com.example.logisticsapi.services;

import com.example.logisticsapi.model.Incident;

import java.util.UUID;

public interface IncidentService {
    Incident register(UUID deliveryId, String description);
}
