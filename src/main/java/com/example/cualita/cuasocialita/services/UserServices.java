package com.example.cualita.cuasocialita.services;

import com.example.cualita.cuasocialita.model.user.User;

import org.springframework.data.domain.Page;

public interface UserServices {

    public User registerUser(User user);

    public User getUserByEmail(String email);

    public User getUserByUsername(String username);

    public Page<User> getAllUsers(Integer page, Integer perPage);

    public User findActiveUser(String credential);
}
