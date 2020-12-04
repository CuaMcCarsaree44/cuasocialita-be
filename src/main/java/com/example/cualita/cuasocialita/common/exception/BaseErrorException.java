package com.example.cualita.cuasocialita.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

public class BaseErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public BaseErrorException(){
        super();
    }

    @Getter
    @Setter
    private String code = "500";

    @Getter
    @Setter
    private String message = "";

    @Getter
    @Setter
    private HttpStatus status = HttpStatus.EXPECTATION_FAILED;

    @Getter
    @Setter
    private Object data = null;
}
