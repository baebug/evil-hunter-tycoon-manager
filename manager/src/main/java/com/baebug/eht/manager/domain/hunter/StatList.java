package com.baebug.eht.manager.domain.hunter;

import lombok.Getter;

/**
 * 스탯의 종류와 가중치
 */
@Getter
public enum StatList {
    HP("체력", "hp", 10),
    ATK("공격력", "atk", 10),
    DEF("방어력", "def", 10),
    CRIT("치명타 확률", "crit", 2),
    SPD("공격속도", "spd", 10),
    EVASION("회피력", "evasion", 2),
    SATIETY_MAX("허기", "satiety_max", 10),
    MOOD_MAX("기분", "mood_max", 10),
    STAMINA_MAX("기력", "stamina_max", 10);

    private final String desc;
    private final String option;
    private final int weight;

    StatList(String desc, String option, int weight) {
        this.desc = desc;
        this.option = option;
        this.weight = weight;
    }

    /**
     * Enum 을 순회하며 입력받은 문자열과 option 이 일치하면, 가중치를 반환한다.
     */
    public static int getWeight(String option) {
        int w = 0;
        for (StatList value : StatList.values()) {
            if (value.getOption().equals(option)) {
                w = value.getWeight();
            }
        }
        return w;
    }
}
