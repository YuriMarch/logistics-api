package com.example.logisticsapi.dtos.response;

import com.example.logisticsapi.enums.DeliveryStatus;
import com.example.logisticsapi.model.Addressee;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter @Setter
public class DeliveryResponse {

    private UUID deliveryId;
    private UserResponse user;
    private Addressee addressee;
    private BigDecimal deliveryFee;
    private DeliveryStatus deliveryStatus;
    private OffsetDateTime orderDate;
    private OffsetDateTime completionDate;
}
