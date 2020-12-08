package com.example.cualita.cuasocialita.config;

import javax.servlet.http.HttpServletRequest;

import com.example.cualita.cuasocialita.common.exception.BaseErrorException;
import com.example.cualita.cuasocialita.model.object.BaseResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setStatus(false);
        response.setMessage(ex.getMessage());

        if(ex instanceof BaseErrorException){
            BaseErrorException exc = (BaseErrorException) ex;

            response.setCode(exc.getCode());
            response.setMessage(exc.getMessage());

            if(exc.getData() != null)
                response.setData(exc.getData());
        }

        return new ResponseEntity<>(
                response, 
                HttpStatus.INTERNAL_SERVER_ERROR        
        );
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody ResponseEntity<Object> handleMissingServletRequestParameterException(Exception ex){
        BaseResponse<Object> response = new BaseResponse<>();

        response.setStatus(false);
        response.setMessage("Internal Server Error");
        response.setCode("417");
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(
            response,
            HttpStatus.EXPECTATION_FAILED
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public @ResponseBody String handleNoMethodException(HttpServletRequest request, NoHandlerFoundException ex){
        return ex.getMessage();
    }
}
