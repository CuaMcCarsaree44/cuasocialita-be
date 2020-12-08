package com.example.cualita.cuasocialita.common.exception;

import org.springframework.http.HttpStatus;

public class UsernameIsAlreadyRegisteredException extends BaseErrorException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public UsernameIsAlreadyRegisteredException(String username){
        this.setCode("UMA0003");
        this.setMessage(username + " is already registered.");
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
