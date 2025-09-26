package com.store.ecommercebackend.controllers;


import com.store.ecommercebackend.dto.request.LoginUserRequest;
import com.store.ecommercebackend.dto.request.RegisterUserRequest;
import com.store.ecommercebackend.entities.User;
import com.store.ecommercebackend.exceptions.DuplicateEmailException;
import com.store.ecommercebackend.mappers.UserMapper;
import com.store.ecommercebackend.repositories.UserRepository;

import com.store.ecommercebackend.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    UserService userService;


    // Authenticate a user
    @PostMapping("/users/authenticate")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginUserRequest request,
            HttpServletResponse response
    ) {
        // contains the access token

        // generating refreshToken and its cookie
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }


    }

    // Register a user
    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response
    ) {
        List<?> data = userRepository.findAll();
        System.out.println("data--------" + data);

        if (userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateEmailException("Email is already registered!..");

        var savedUser = userService.register(request);

        return ResponseEntity.ok(savedUser);
    }
}
