package com.example.property_management.controllers;

import com.example.property_management.Services.ReviewService;
import com.example.property_management.entity.Review;
import com.example.property_management.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/offer")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class OfferController {

    private final ReviewService reviewService;

    @PostMapping("/create-offer/{user_id}")
    public Review makeOffer(@PathVariable Long user_id, @RequestBody Review offer){
        return reviewService.addOffer(user_id,offer);
    }

}
