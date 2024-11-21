package com.example.__words_ielts.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    ADMIN(0),
    USER(1);

    private final int value;

    UserRoleEnum(int i) {
        value = i;
    }

}
