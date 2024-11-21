package com.example.__words_ielts.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    ACTIVE(0),
    INACTIVE(1),
    BANNED(2);

    private final int value;

    UserStatusEnum(int i) {
        value = i;
    }

}
