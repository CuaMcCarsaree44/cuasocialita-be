package com.example.cualita.cuasocialita.controller;

import javax.validation.Valid;

import com.example.cualita.cuasocialita.model.object.BaseResponse;
import com.example.cualita.cuasocialita.model.object.dto.auth.request.LoginDTO;
import com.example.cualita.cuasocialita.model.object.dto.auth.response.ResponseLoginDTO;
import com.example.cualita.cuasocialita.model.user.User;
import com.example.cualita.cuasocialita.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    
    @Autowired
    private UserServices userServices;
    
    @PostMapping("/login")
    public BaseResponse<ResponseLoginDTO> login(
        @Valid @RequestBody LoginDTO loginDTO,
        BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);

            return new BaseResponse<>(
                false,
                "417",
                fieldError.getField() + " field " + fieldError.getDefaultMessage(),
                null
            );
        }

        /**
         * Check User is neither not exist, suspended, or not activated.
         */
        User user = this.userServices.findActiveUser(loginDTO.getCredential());

        if(user == null)
            return new BaseResponse<>(
                false,
                "404",
                "Credential not found",
                null
            );
        else if(user.getStatus() == -1){
            return new BaseResponse<>(
                false,
                "422",
                "User is not activated.",
                null
            );
        }else if(user.getStatus() == 0){
            return new BaseResponse<>(
                false,
                "422",
                "User is suspended.",
                null
            );
        }
        

        ResponseLoginDTO response = new ResponseLoginDTO();
        response.setAccessToken("lalala");
        response.setUserData(new User());
        
        return new BaseResponse<>(
            true,
            "200",
            "Login success",
            response
        );
    }
}
