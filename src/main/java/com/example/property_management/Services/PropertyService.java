package com.example.property_management.Services;

import com.example.property_management.entity.ImageModel;
import com.example.property_management.entity.PropType;
import com.example.property_management.entity.Property;
import com.example.property_management.entity.User;
import com.example.property_management.repository.ImageModelRepository;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageModelRepository imageModelRepository;

    // find all isListed properties
    public List<Property> getAllPropertyListed(){
        return propertyRepository.findAll().stream().filter(p->p.isIslisted()==true).collect(Collectors.toList());
    }

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

    public String deleteProperty(Long id){
        try{
            propertyRepository.deleteById(id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "success";
    }

    public Property changeListed(Long id){
        Property myProp = propertyRepository.findById(id).get();
        boolean listStat = myProp.isIslisted();
        myProp.setIslisted(!listStat);
        propertyRepository.save(myProp);
        return myProp;
    }

    public List<Property> getfileredProperties(String propType , int bedroom , double price,String state){

            return propertyRepository.findAll().stream()
                    .filter(p->p.getPtype().equals(propType))
                    .filter(p->p.getBedrooms()>bedroom)
                    .filter(p->p.getPrice()<price)
                    .filter(p->p.getAddress().getState().equals(state))
                    .collect(Collectors.toList());

    }

}
