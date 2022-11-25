package com.example.property_management.controllers;

import com.example.property_management.Services.UserService;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.entity.dto.PropertyListDto;
import com.example.property_management.entity.dto.UserDto;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/dto")
    public List<UserDto> getAllUsersDto(){
        return userService.getAllUser().stream()
                .map(property -> modelMapper.map(property, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/role/{role}")
    public List<UserDto> getUserByRole (@PathVariable String role){
        return userService.getUserByRole(role).stream()
                .map(property -> modelMapper.map(property, UserDto.class))
                .collect(Collectors.toList());
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
