package com.example.cualita.cuasocialita.controller;

import javax.validation.Valid;

import com.example.cualita.cuasocialita.helper.Encryption;
import com.example.cualita.cuasocialita.model.object.BaseResponse;
import com.example.cualita.cuasocialita.model.object.dto.user.RegisterUserDTO;
import com.example.cualita.cuasocialita.model.user.User;
import com.example.cualita.cuasocialita.services.impl.UserServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserServicesImpl userServices;
    
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
        @Valid @RequestBody RegisterUserDTO registerUserDTO, 
        BindingResult bindingResult){

            if(bindingResult.hasErrors()){
                FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);

                return new BaseResponse<>(
                    false,
                    "417",
                    fieldError.getField() + " field " + fieldError.getDefaultMessage(),
                    null
                );
            }

            User user = new User();
            user.setUsername(registerUserDTO.getUsername());
            user.setPassword(new Encryption().make(registerUserDTO.getPassword()));
            user.setEmail(registerUserDTO.getEmail());
            user.setStatus(1);
            
            userServices.registerUser(user);

            return new BaseResponse<>(
                        true, "200", "Data in!", user);
    }
}
