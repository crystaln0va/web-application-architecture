package com.example.property_management.repository;

import com.example.property_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);
}
