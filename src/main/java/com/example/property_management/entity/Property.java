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
    private PropType ptype;
    private HomeType htype;
    private String title;
    private String description;
    private boolean islisted;
    private Status status;

    private int bedrooms;
    private int bathrooms;
    private boolean ispetfriendly;
    private int views;
    private double price;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "prop_address")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ImageModel> images;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "key_user")
    private User user;
}
