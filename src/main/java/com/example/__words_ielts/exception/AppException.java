package com.example.__words_ielts.exception;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{
    public AppException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }
    private ErrorCodeEnum errorCodeEnum;
}
