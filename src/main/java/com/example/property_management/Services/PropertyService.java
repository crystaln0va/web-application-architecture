package com.example.property_management.Services;

import com.example.property_management.entity.Property;
import com.example.property_management.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }


    public Property addProperty(Property prop){
        return propertyRepository.save(prop);
    }

    public Property getPropertyById(Long id){
        return propertyRepository.findById(id).get();
    }
}
