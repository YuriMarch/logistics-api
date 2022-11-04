package com.example.logisticsapi.services.impl;

import com.example.logisticsapi.exceptions.BusinessException;
import com.example.logisticsapi.model.User;
import com.example.logisticsapi.repositories.UserRepository;
import com.example.logisticsapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User searchUser(UUID userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new BusinessException("User not found."));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return Optional.ofNullable(userRepository.findByUserId(userId));
    }

    @Override
    public void saveUser(User user) {
        boolean isEmailUsed = userRepository.findByEmail(user.getEmail())
                .stream().anyMatch(existingUser -> !existingUser.equals(user));
        if (isEmailUsed){
            throw new BusinessException("Email already registered.");
        }
        userRepository.save(user);
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        userRepository.deleteById(userId);
    }
}
