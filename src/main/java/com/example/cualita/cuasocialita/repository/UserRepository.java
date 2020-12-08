package com.example.cualita.cuasocialita.repository;

import java.util.UUID;

import com.example.cualita.cuasocialita.model.user.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, UUID> {
    
    @Transactional
    @Query("SELECT X FROM User X WHERE X.email=?1 AND X.deletedAt = null")
    public User findByEmail(String email);

    @Transactional
    @Query("SELECT X FROM User X WHERE X.username=?1 AND X.deletedAt = null")
    public User findByUsername(String username);

    @Transactional
    @Query("SELECT X FROM User X WHERE X.deletedAt = null")
    public Page<User> getAllUsers(PageRequest request);
}
