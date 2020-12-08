package com.example.cualita.cuasocialita.model.object.dto.auth.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    
    @Valid
    @NotNull
    private String credential;
    
    @Valid
    @NotNull
    private String password;
}
