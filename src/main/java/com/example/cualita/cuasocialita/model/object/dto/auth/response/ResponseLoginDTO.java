package com.example.cualita.cuasocialita.model.object.dto.auth.response;

import com.example.cualita.cuasocialita.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLoginDTO {
    private String accessToken;
    private User userData;
}
