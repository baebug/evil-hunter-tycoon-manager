package com.baebug.eht.manager.domain.item;

import lombok.Getter;

@Getter
public enum OptionList {
    ATK("전체 공격력", "atk"),
    DEF("전체 방어력", "def"),
    HP("체력", "hp"),
    EVASION("회피력", "evasion"),
    SPD("공격속도", "spd"),
    CRIT("치명타 확률", "crit"),
    CRIT_DMG("치명타 피해량", "crit_dmg"),
    ADD_DMG("추가 데미지", "add_dmg"),
    PRIMATE("영장 데미지", "primate"),
    DEMON("악마류 데미지", "demon"),
    UNDEAD("언데드 데미지", "undead"),
    ANIMAL("동물류 데미지", "animal"),
    BOSS("보스류 데미지", "boss"),
    DMG_TAKEN("받는 데미지 25% 감소", "dmg_taken"),
    LIFE_STEAL("흡혈", "life_steal"),
    DARK_LORD("5초간 대악마로 변신", "dark_lord"),
    DODGE("1렙의 닷지 시전", "dodge"),
    STUN("2초간 스턴", "stun"),
    MATERIAL("재료 추가 획득", "material"),
    SATIETY("허기 소모량", "satiety"),
    MOOD("기분 소모량", "mood"),
    SATIETY_UP("3%의 허기 회복", "satiety_up"),
    MOOD_UP("3%의 기분 회복", "mood_up");

    private final String desc;
    private final String option;

    OptionList(String desc, String option) {
        this.desc = desc;
        this.option = option;
    }

}
