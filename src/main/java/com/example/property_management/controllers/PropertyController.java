package com.example.property_management.controllers;

import com.example.property_management.entity.Property;
import com.example.property_management.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    @GetMapping
    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }
}
