package com.example.property_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private PropType pType;
    private HomeType hType;
    private String title;
    private String description;
    private boolean isListed;
    private Status status;

    private int bedrooms;
    private int bathrooms;
    private boolean isPetFriendly;
    private int views;
    private String address;
    private double price;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ImageModel> images;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "key_user")
    private User user;
}
