package com.example.logisticsapi.model;

import com.example.logisticsapi.validation.ValidationGroups;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false)
    @NotNull(groups = ValidationGroups.UserId.class)
    private UUID userId;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 4, max = 255)
    private String name;

    @Column(name = "email", nullable = false)
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    @NotBlank
    @Size(max = 20)
    private String phoneNumber;
}
