package com.example.logisticsapi.services;

import com.example.logisticsapi.model.Delivery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryService {

    Delivery requestDelivery(Delivery delivery);

    List<Delivery> getAllDeliveries();

    Optional<Delivery> getDeliveryById(UUID deliveryId);

    Delivery searchDelivery(UUID deliveryId);
}
