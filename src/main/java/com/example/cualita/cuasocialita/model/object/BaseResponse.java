package com.example.cualita.cuasocialita.model.object;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse<T> {
    private Boolean status;
    private Integer code;
    private String message;
    private T data;

    public BaseResponse(Boolean status, Integer code, String message, T data){
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
