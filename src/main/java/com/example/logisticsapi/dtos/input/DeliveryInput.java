package com.example.logisticsapi.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter @Setter
public class DeliveryInput {

    @NotNull
    @Valid
    private UserIdInput user;

    @NotNull
    @Valid
    private AddresseeInput addressee;

    @NotNull
    private BigDecimal deliveryFee;
}
