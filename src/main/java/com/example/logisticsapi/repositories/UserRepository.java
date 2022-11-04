package com.example.logisticsapi.repositories;

import com.example.logisticsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByName(String name);
    List<User> findByNameContaining(String name);
    List<User> findByEmailContaining(String email);
    Optional<User> findByEmail(String email);

    User findByUserId(UUID userId);
}
