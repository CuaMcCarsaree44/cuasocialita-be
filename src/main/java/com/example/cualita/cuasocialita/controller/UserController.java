package com.example.cualita.cuasocialita.controller;

import javax.validation.Valid;

import com.example.cualita.cuasocialita.common.Pagination;
import com.example.cualita.cuasocialita.common.exception.EmailIsAlreadyRegisteredException;
import com.example.cualita.cuasocialita.common.exception.UsernameIsAlreadyRegisteredException;
import com.example.cualita.cuasocialita.helper.Encryption;
import com.example.cualita.cuasocialita.model.object.BaseResponse;
import com.example.cualita.cuasocialita.model.object.dto.user.RegisterUserDTO;
import com.example.cualita.cuasocialita.model.user.User;
import com.example.cualita.cuasocialita.services.impl.UserServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", 
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
     * @param   email => String (unique)
     * @param   username => String (unique)
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

            if(userServices.getUserByEmail(registerUserDTO.getEmail().toLowerCase()) != null){
                throw new EmailIsAlreadyRegisteredException(registerUserDTO.getEmail().toLowerCase());
            }
            
            if(userServices.getUserByUsername(registerUserDTO.getUsername().toLowerCase()) != null){
                throw new UsernameIsAlreadyRegisteredException(registerUserDTO.getUsername().toLowerCase());
            }

            User user = new User();
            user.setUsername(registerUserDTO.getUsername().toLowerCase());
            user.setPassword(new Encryption().make(registerUserDTO.getPassword()));
            user.setEmail(registerUserDTO.getEmail().toLowerCase());
            user.setStatus(-1);
            
            userServices.registerUser(user);

            return new BaseResponse<>(
                        true, "200", "Data in!", user);
    }



    @GetMapping(value = "/")
    public BaseResponse<Pagination<User>> getAllUsers(
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "per_page", required = false) Integer perPage
    ){
        if(page == null || page <= 0) page = 1;
        if(perPage == null || perPage <= 0) perPage = 10;

        return new BaseResponse<>(
            true,
            "200",
            "Success getting all users data.",
            new Pagination<User>().convertPageable(this.userServices.getAllUsers(page - 1, perPage), "")
        );
    }
}
