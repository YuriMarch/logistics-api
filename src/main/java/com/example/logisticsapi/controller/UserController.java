package com.example.logisticsapi.controller;

import com.example.logisticsapi.model.User;
import com.example.logisticsapi.repositories.UserRepository;
import com.example.logisticsapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserByUserId(@PathVariable UUID userId) {
        Optional<User> user = userService.findByUserId(userId);

        return user.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId,@Valid @RequestBody User user){
        if (!userService.existsByUserId(userId)){
            ResponseEntity.notFound().build();
        }
        user.setUserId(userId);
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){
        if (!userService.existsByUserId(userId)){
            ResponseEntity.notFound().build();
        }
        userService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
