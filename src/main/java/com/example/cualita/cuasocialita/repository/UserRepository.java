package com.example.cualita.cuasocialita.repository;

import java.util.UUID;

import com.example.cualita.cuasocialita.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID> {
    
}
