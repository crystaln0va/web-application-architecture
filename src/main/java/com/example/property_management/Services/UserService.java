package com.example.property_management.Services;


import com.example.property_management.entity.User;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(long id){
        return userRepository.findById(id).get();
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

}
