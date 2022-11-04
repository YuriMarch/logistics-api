package com.example.logisticsapi.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AddresseeInput {

    @NotBlank
    private String name;

    @NotBlank
    private String streetName;

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String city;
}
