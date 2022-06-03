package com.baebug.eht.manager.domain.hunter;

import lombok.Getter;

@Getter
public enum TechList {
    HP("체력", "hp", .15),
    MOOD_MAX("기분 최대치", "mood_max", .3),
    SATIETY_MAX("허기 최대치", "satiety_max", .3),
    STAMINA_MAX("기력 최대치", "stamina_max", .3),
    ATK("공격력", "atk", .15),
    DEF("방어력", "def", .15),
    CRIT("치명타 확률", "crit", .1),
    SPD("공격속도", "spd", .1),
    EVASION("회피력", "evasion", .1),
    WALK("이동속도", "walk", .15),
    SKILL1("스킬1 쿨타임 감소", "skill1", .1),
    SKILL2("스킬2 쿨타임 감소", "skill2", .1),
    MOOD("기분 소모량", "mood", .3),
    SATIETY("허기 소모량", "satiety", .3),
    STAMINA("기력 소모량", "stamina", .3);

    private final String desc;
    private final String option;
    private final Double weight;

    TechList(String desc, String option, Double weight) {
        this.desc = desc;
        this.option = option;
        this.weight = weight;
    }
}
