package com.example.property_management.entity.dto;


import com.example.property_management.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private long id;
    private String fname;
    private String lname;
    private Role role;
    private boolean isactive;

}
