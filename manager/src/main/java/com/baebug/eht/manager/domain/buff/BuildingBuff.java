package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 건물 버프 클래스
 */
@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingBuff {

    /**
     * 건물 버프 레벨
     * int type
     */
    private int atk;
    private int def;
    private int hp;
    private int crit_dmg;
    private int satiety_max;
    private int mood_max;
    private int stamina_max;
    private int walk;

    /**
     * 건물 버프의 레벨을 입력받는 메서드
     * @param satietyLevel  허기, 기력, 기분 증가 레벨
     */
    public void setBuildingBuff(int atkLevel, int defLevel, int hpLevel, int critDmgLevel, int walkLevel, int satietyLevel) {
        this.atk = atkLevel;
        this.def = defLevel;
        this.hp = hpLevel;
        this.crit_dmg = critDmgLevel;
        this.walk = walkLevel;
        this.satiety_max = satietyLevel;
        this.mood_max = satietyLevel;
        this.stamina_max = satietyLevel;
    }

    /**
     * 클래스의 필드를 순회하며 입력받은 건물 레벨을 능력치로 합산한다.
     * 모든 건물의 초기값은 5 이고, 가중치는 .15 이다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            int level = (int) field.get(this);
            double value = 5 + level * .15;

            specDTO.add(field.getName(), value);
        }
    }

}
