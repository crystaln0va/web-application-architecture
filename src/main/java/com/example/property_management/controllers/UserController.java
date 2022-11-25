package com.example.property_management.controllers;

import com.example.property_management.Services.UserService;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {


    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


    @GetMapping("/get-user-info")
    public User getByUsername(@RequestParam String email){
        return userService.getUserByUsername(email);
    }

    @PutMapping("/favorite/{prop_id}/{user_id}")
    public List<Property> addFavoriteList(@PathVariable Long prop_id,@PathVariable Long user_id){
        return userService.addPropertyToFavorite(prop_id,user_id);
    }

    @GetMapping("/favorite/{user_id}")
    public List<Property> getFavorite(@PathVariable Long user_id){
        return userService.getAllfavorite(user_id);
    }
}
