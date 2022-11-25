package com.example.property_management.repository;

import com.example.property_management.entity.Role;
import com.example.property_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

    List<User> findUsersByRoleContaining(String role);
}
