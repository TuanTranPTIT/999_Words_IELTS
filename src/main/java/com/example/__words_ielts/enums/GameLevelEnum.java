package com.example.__words_ielts.enums;

import lombok.Getter;

@Getter
public enum GameLevelEnum {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    GameLevelEnum(int i) {
        value = i;
    }
}
