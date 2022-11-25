package com.example.property_management.entity.dto;

import com.example.property_management.entity.HomeType;
import com.example.property_management.entity.ImageModel;
import com.example.property_management.entity.PropType;
import com.example.property_management.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyListDto {
    private long id;
    private PropType ptype;
    private HomeType htype;
    private Status status;
    private String title;
    private String description;
    private boolean islisted;
    private int bedrooms;
    private int bathrooms;
    private int views;
    private double price;
    private List<ImageModel> image;
}
