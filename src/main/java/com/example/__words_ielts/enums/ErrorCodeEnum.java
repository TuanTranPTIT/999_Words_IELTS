package com.example.__words_ielts.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCodeEnum {
    UNCATEGORIZED_EXCEPTION("9999","Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_INVALID("KEY0000","enumKey không hợp lệ", HttpStatus.BAD_REQUEST),

    //USER
    EMAIL_EXISTED("USER0000", "Email đã tồn tại", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID("USER0002","Email không hợp lệ", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_BLANK("USER0003","Mật khẩu không được chứa khoảng trống", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID("USER0004","Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, ký tự đặc biệt và số", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EXISTED("USER0005", "Email không tồn tại", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("USER0006", "Unauthenticated", HttpStatus.UNAUTHORIZED),
    PASSWORD_INCORRECT("USER0007", "Mật khẩu không đúng", HttpStatus.BAD_REQUEST),

    //TOPIC
    TOPIC_NOT_EXISTED("TOPIC0000", "Topic không tồn tại", HttpStatus.NOT_FOUND),
    ID_TOPIC_INVALID("TOPIC0001", "Id topic không hợp lệ", HttpStatus.BAD_REQUEST),

    //WORD
    WORD_NOT_EXISTED("WORD0000", "Từ không tồn tại", HttpStatus.NOT_FOUND)

    ;
    private final String code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCodeEnum(String code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
