package com.example.logisticsapi.services.impl;

import com.example.logisticsapi.enums.DeliveryStatus;
import com.example.logisticsapi.exceptions.BusinessException;
import com.example.logisticsapi.model.Delivery;
import com.example.logisticsapi.repositories.DeliveryRepository;
import com.example.logisticsapi.services.CompletedDeliveryService;
import com.example.logisticsapi.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompletedDeliveryServiceImpl implements CompletedDeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryService deliveryService;

    public void complete(UUID deliveryId){
        Delivery delivery = deliveryService.searchDelivery(deliveryId);
        delivery.complete();

        deliveryRepository.save(delivery);
    }
}
