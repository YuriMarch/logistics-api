package com.example.logisticsapi.services.impl;

import com.example.logisticsapi.model.Incident;
import com.example.logisticsapi.exceptions.BusinessException;
import com.example.logisticsapi.model.Delivery;
import com.example.logisticsapi.services.DeliveryService;
import com.example.logisticsapi.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    DeliveryService deliveryService;

    @Override
    @Transactional
    public Incident register(UUID deliveryId, String description){
        Delivery delivery = deliveryService.searchDelivery(deliveryId);
        return delivery.addIncident(description);
    }
}
