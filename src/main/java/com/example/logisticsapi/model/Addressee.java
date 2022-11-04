package com.example.logisticsapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@Embeddable
public class Addressee {

    @NotBlank
    @Column(name = "addressee_name")
    private String name;

    @NotBlank
    @Column(name = "addressee_street_name")
    private String streetName;

    @NotBlank
    @Column(name = "addressee_street_number")
    private String streetNumber;

    @NotBlank
    @Column(name = "addressee_city")
    private String city;
}
