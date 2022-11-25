package com.example.property_management.controllers;

import com.example.property_management.Services.PropertyService;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.dto.NewPropertyDto;
import com.example.property_management.entity.dto.PropertyListDto;
import com.example.property_management.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PropertyController {

    private final PropertyService propertyService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<PropertyListDto> getAllProperty(){
        return propertyService.getAllProperty().stream()
                .map(property -> modelMapper.map(property,PropertyListDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id){
        return propertyService.getPropertyById(id);
    }

    @PostMapping("/{user_id}")
    public List<Property> addProperty(@RequestBody NewPropertyDto newProp, @PathVariable long user_id) throws IOException {
        Property prop = newProp.getProperty();
        MultipartFile image= newProp.getFile();
        return propertyService.addProperty(image,prop,user_id);
    }
}
