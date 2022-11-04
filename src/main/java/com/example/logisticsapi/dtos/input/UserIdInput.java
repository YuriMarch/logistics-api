package com.example.logisticsapi.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter @Setter
public class UserIdInput {

    @NotNull
    private UUID userId;
}
