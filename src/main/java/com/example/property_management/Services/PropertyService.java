package com.example.property_management.Services;

import com.example.property_management.entity.ImageModel;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.repository.ImageModelRepository;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageModelRepository imageModelRepository;

    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }


    public List<Property> addProperty(ImageModel img, Property prop,Long user_id) throws IOException {

        ImageModel savedImage = imageModelRepository.save(img);
        prop.getImage().add(savedImage);
        User user = userService.getUserById(user_id);
        prop.setUser_id(user_id);
        Property myProp = propertyRepository.save(prop);
        user.getProperties().add(myProp);
        userRepository.save(user);
        return user.getProperties();
    }

    public Property getPropertyById(Long id){
        return propertyRepository.findById(id).get();
    }
}
