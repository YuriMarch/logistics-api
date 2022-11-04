package com.example.logisticsapi.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UserResponse {

    private UUID userId;
    private String userName;
}
