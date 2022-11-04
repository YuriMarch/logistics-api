package com.example.logisticsapi.services.impl;

import com.example.logisticsapi.enums.DeliveryStatus;
import com.example.logisticsapi.exceptions.BusinessException;
import com.example.logisticsapi.exceptions.EntityNotFoundException;
import com.example.logisticsapi.model.Delivery;
import com.example.logisticsapi.model.User;
import com.example.logisticsapi.repositories.DeliveryRepository;
import com.example.logisticsapi.services.DeliveryService;
import com.example.logisticsapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserService userService;

    @Override
    public Delivery searchDelivery(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found."));
    }

    @Override
    public Delivery requestDelivery(Delivery delivery){
        User user = userService.searchUser(delivery.getUser().getUserId());
        delivery.setUser(user);
        delivery.setDeliveryStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Optional<Delivery> getDeliveryById(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId);
    }
}
