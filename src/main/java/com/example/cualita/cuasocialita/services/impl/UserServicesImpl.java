package com.example.cualita.cuasocialita.services.impl;

import com.example.cualita.cuasocialita.common.exception.GetAllUsersException;
import com.example.cualita.cuasocialita.common.exception.RegisterUserException;
import com.example.cualita.cuasocialita.model.user.User;
import com.example.cualita.cuasocialita.repository.UserRepository;
import com.example.cualita.cuasocialita.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    public static final CharSequence DOMAIN_SLICER = "@";

    @Override
    public User registerUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RegisterUserException("Registering user encountered error: " + e.getMessage());
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new RegisterUserException("Searching user by email encountered error: " + e.getMessage());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new RegisterUserException("Searching user by username encountered error: " + e.getMessage());
        }
    }

    @Override
    public Page<User> getAllUsers(Integer page, Integer perPage) {
        try{
            return userRepository.getAllUsers(PageRequest.of(page, perPage));
        }catch(Exception e){
            throw new GetAllUsersException(e.getMessage());            
        }
    }

    @Override
    public User findActiveUser(String credential) {
        try{
            if(credential.contains(UserServicesImpl.DOMAIN_SLICER))
                return userRepository.findByEmail(credential);

            return userRepository.findByUsername(credential);  
        }catch(Exception e){
            throw new GetAllUsersException(e.getMessage());            
        }
    }


}
