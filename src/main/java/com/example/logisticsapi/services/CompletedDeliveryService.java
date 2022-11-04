package com.example.logisticsapi.services;

import java.util.UUID;

public interface CompletedDeliveryService {
    void complete(UUID deliveryId);
}
