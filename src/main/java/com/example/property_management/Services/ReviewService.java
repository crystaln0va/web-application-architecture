package com.example.property_management.Services;


import com.example.property_management.entity.Property;
import com.example.property_management.entity.Review;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;

    public Review addOffer(Long prop_id, Review offer){

        Property myProp = propertyRepository.findById(prop_id).get();
        Review myOffer = reviewRepository.save(offer);
        myProp.getReviews().add(myOffer);
        propertyRepository.save(myProp);
        return myOffer;
    }


}
