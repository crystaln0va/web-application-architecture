package com.example.property_management.Services;

import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }


    public List<Property> addProperty(Property prop,Long user_id){
        User user = userService.getUserById(user_id);
        user.getProperties().add(prop);
        prop.setUser_id(user_id);
        userRepository.save(user);
        return user.getProperties();
    }

    public Property getPropertyById(Long id){
        return propertyRepository.findById(id).get();
    }
}
