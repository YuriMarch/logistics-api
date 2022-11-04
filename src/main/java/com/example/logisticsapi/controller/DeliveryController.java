package com.example.logisticsapi.controller;

import com.example.logisticsapi.assembler.DeliveryAssembler;
import com.example.logisticsapi.dtos.input.DeliveryInput;
import com.example.logisticsapi.dtos.response.DeliveryResponse;
import com.example.logisticsapi.model.Delivery;
import com.example.logisticsapi.services.CompletedDeliveryService;
import com.example.logisticsapi.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    DeliveryAssembler deliveryAssembler;

    @Autowired
    CompletedDeliveryService completedDeliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponse requestDelivery(@Valid @RequestBody DeliveryInput deliveryInput){
        Delivery requestedDelivery = deliveryAssembler.toEntity(deliveryInput);
        return deliveryAssembler.toResponse(deliveryService.requestDelivery(requestedDelivery));
    }

    @GetMapping
    public List<DeliveryResponse> getAllDeliveries(){
        return deliveryAssembler.toResponseList(deliveryService.getAllDeliveries());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponse> getDeliveryById(@PathVariable UUID deliveryId){
        return deliveryService.getDeliveryById(deliveryId)
                .map(delivery -> ResponseEntity.ok(deliveryAssembler.toResponse(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{deliveryId}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeDelivery(@PathVariable UUID deliveryId){
        completedDeliveryService.complete(deliveryId);
    }
}
