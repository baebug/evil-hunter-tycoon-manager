package com.baebug.eht.manager.domain.hunter;

import lombok.Getter;

@Getter
public enum StatGrade {
    Gray(0),
    Blue(1),
    Orange(2),
    Purple(3);

    private final int code;

    StatGrade(int code) {
        this.code = code;
    }
}
