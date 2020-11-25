package com.example.cualita.cuasocialita.controller;

import javax.validation.Valid;

import com.example.cualita.cuasocialita.model.object.BaseResponse;
import com.example.cualita.cuasocialita.model.object.dto.user.RegisterUserDTO;
import com.example.cualita.cuasocialita.model.user.User;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    
    /** 
     * registerUser
     * An API to register user
     * 
     * @author  Yosua Kristianto
     * @version 1.0
     * @since   2020-11-25
     * @param   email => String 
     * @param   username => String
     * @param   password => String
     * @return  BaseResponse => json
     */
    @PostMapping(value = "/register")
    public BaseResponse<User> registerUser(
        @Valid @RequestBody RegisterUserDTO registerUserDTO){

        return new BaseResponse<>(
                    true, 200, "Data in!", new User(
                        registerUserDTO.getEmail(),
                        registerUserDTO.getPassword(),
                        registerUserDTO.getUsername()
                    ));
    }
}
