package com.example.logisticsapi.controller;

import com.example.logisticsapi.assembler.IncidentAssembler;
import com.example.logisticsapi.dtos.input.IncidentInput;
import com.example.logisticsapi.dtos.response.IncidentResponse;
import com.example.logisticsapi.model.Delivery;
import com.example.logisticsapi.model.Incident;
import com.example.logisticsapi.services.DeliveryService;
import com.example.logisticsapi.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries/{deliveryId}/incidents")
public class IncidentController {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    IncidentService incidentService;

    @Autowired
    IncidentAssembler incidentAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncidentResponse registerIncident(@PathVariable UUID deliveryId,
                                             @Valid @RequestBody IncidentInput incidentInput){
        Incident registeredIncident = incidentService.register(deliveryId, incidentInput.getDescription());
        return incidentAssembler.toResponse(registeredIncident);
    }

    @GetMapping
    public List<IncidentResponse> getIncidents(@PathVariable UUID deliveryId){
        Delivery delivery = deliveryService.searchDelivery(deliveryId);

        return incidentAssembler.toIncidentList(delivery.getIncidents());
    }
}
