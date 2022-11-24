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
    private boolean islisted=true;
    private Status status=Status.ACTIVE;
    private String image;

    private int bedrooms;
    private int bathrooms;
    private boolean ispetfriendly;
    private int views=0;
    private double price;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "prop_address")
    private Address address;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Review> reviews;


    private Long user_id;
}
