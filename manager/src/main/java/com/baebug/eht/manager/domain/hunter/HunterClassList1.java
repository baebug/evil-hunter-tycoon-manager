package com.baebug.eht.manager.domain.hunter;

import lombok.Getter;

@Getter
public enum HunterClassList1 {
    BERSERKER("버서커"),
    RANGER("레인저"),
    PALADIN("팔라딘"),
    SORCERER("소서러");

    private final String name;

    HunterClassList1(String name) {
        this.name = name;
    }
}
