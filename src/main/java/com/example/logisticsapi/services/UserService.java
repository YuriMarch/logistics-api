package com.example.logisticsapi.services;

import com.example.logisticsapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    User searchUser(UUID userId);

    Optional<User> findByUserId(UUID userId);

    void saveUser(User user);

    boolean existsByUserId(UUID userId);

    void deleteByUserId(UUID userId);
}
