package com.example.cualita.cuasocialita.services.impl;

import com.example.cualita.cuasocialita.common.exception.RegisterUserException;
import com.example.cualita.cuasocialita.model.user.User;
import com.example.cualita.cuasocialita.repository.UserRepository;
import com.example.cualita.cuasocialita.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;


    @Override
    public User registerUser(User user) {
        try{
            userRepository.save(user);
        }catch(Exception e){
            throw new RegisterUserException(e.getMessage());
        }

        return user;
    }
    
}
