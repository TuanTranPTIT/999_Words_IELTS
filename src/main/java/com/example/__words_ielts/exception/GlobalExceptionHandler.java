package com.example.__words_ielts.exception;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCodeEnum.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCodeEnum.UNCATEGORIZED_EXCEPTION.getMessage());

        return  ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCodeEnum errorCodeEnum = exception.getErrorCodeEnum();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCodeEnum.getCode());
        apiResponse.setMessage(errorCodeEnum.getMessage());

        return ResponseEntity.status(errorCodeEnum.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.KEY_INVALID;

        try {
            errorCodeEnum = ErrorCodeEnum.valueOf(enumKey);
        } catch (IllegalArgumentException e){

        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCodeEnum.getCode());
        apiResponse.setMessage(errorCodeEnum.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }


}
