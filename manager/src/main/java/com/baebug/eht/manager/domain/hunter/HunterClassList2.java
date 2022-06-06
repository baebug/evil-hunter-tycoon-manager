package com.baebug.eht.manager.domain.hunter;

import lombok.Getter;

@Getter
public enum HunterClassList2 {
    DUELIST("듀얼리스트"),
    SLAYER("슬레이어"),
    WARRIOR("워리어"),
    HAWK_EYE("호크아이"),
    SNIPER("스나이퍼"),
    SUMMONIC_ARCHER("서모닉아처"),
    TEMPLAR("템플러"),
    CRUSADER("크루세이더"),
    DARK_PALADIN("다크팔라딘"),
    ARCH_MAGE("아크메이지"),
    DARK_MAGE("다크메이지"),
    IGNIS("이그니스");

    private final String name;

    HunterClassList2(String name) {
        this.name = name;
    }
}
