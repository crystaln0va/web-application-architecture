package com.example.property_management.controllers;

import com.example.property_management.Services.UserService;
import com.example.property_management.entity.User;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {


    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/get-user-info/:{username}")
    public User getByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
}
