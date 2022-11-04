package com.example.logisticsapi.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class IncidentInput {

    @NotBlank
    private String description;
}
