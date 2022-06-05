package com.baebug.eht.manager.domain.buff;

import lombok.Getter;

import java.util.Objects;

/**
 * 건물 버프의 종류와 가중치
 * 모든 버프의 초기값과 가중치가 같아서, 현재는 사용하지 않는다.
 */
@Getter
public enum BuildingList {
    CRIT_DMG("치명타 피해량 증가", "crit_dmg", .15),
    ATK("공격력", "atk", .15),
    WALK("이동속도", "walk", .15),
    DEF("방어력", "def", .15),
    HP("체력", "hp", .15),
    MOOD_MAX("기분 최대치", "mood_max", .15),
    SATIETY_MAX("허기 최대치", "satiety_max", .15),
    STAMINA_MAX("기력 최대치", "stamina_max", .15);

    private final String desc;
    private final String option;
    private final Double weight;

    BuildingList(String desc, String option, Double weight) {
        this.desc = desc;
        this.option = option;
        this.weight = weight;
    }

    public static Double getWeight(String option) {
        double w = .0;
        for (BuildingList value : BuildingList.values()) {
            if (Objects.equals(value.getOption(), option)) {
                w = value.getWeight();
            }
        }
        return w;
    }
}
