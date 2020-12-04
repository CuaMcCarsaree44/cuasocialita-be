package com.example.cualita.cuasocialita.common.exception;

import org.springframework.http.HttpStatus;

public class RegisterUserException extends BaseErrorException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public RegisterUserException(String message){
        this.setCode("UMA0001");
        this.setMessage(message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
