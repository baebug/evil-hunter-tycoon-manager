package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingBuff {

    private int atk;
    private int def;
    private int hp;
    private int crit_dmg;
    private int satiety_max;
    private int mood_max;
    private int stamina_max;
    private int walk;

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
     * 건물 레벨을 입력받고
     * buildingList 에서 필드명으로 가중치를 찾아서 (초기값: 5%, 가중치: .15)
     * 건물레벨 * 가중치 연산
     */
    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            int level = (int) field.get(this);
            double value = 5 + level * .15;

            specDto.add(field.getName(), value);
        }
    }

}
