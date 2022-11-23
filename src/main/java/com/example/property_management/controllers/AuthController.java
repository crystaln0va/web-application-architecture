package com.example.property_management.controllers;


import com.example.property_management.Services.AuthService;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.entity.dto.LoginRequestDto;
import com.example.property_management.entity.dto.RefreshRequestDto;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authenticate")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    private final UserRepository userRepository;

    private final AuthService authService;

    @PostMapping("/register")
    public User addNewUser(@RequestBody User user){
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequestDto refreshRequest){
        String oldToken = refreshRequest.getRefreshToken();
        return ResponseEntity.ok(authService.refresh(oldToken));
    }



}
