package com.example.cualita.cuasocialita.common.exception;

import org.springframework.http.HttpStatus;

public class FindActiveUserException extends BaseErrorException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public FindActiveUserException(String message){
        this.setCode("UMA0005");
        this.setMessage("Retrieve active users data error: "+ message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}