package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 도감 수집 버프 클래스
 */
@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionBasicBuff {

    /**
     * 도감 수집으로 적용되는 버프 옵션
     * double type
     */
    private double atk;
    private double def;
    private double hp;
    private double crit;
    private double crit_dmg;
    private double satiety;
    private double mood;
    private double stamina;
    private double primate;

    /**
     * 도감 수집 버프를 입력받는 메서드
     */
    public void setCollectionBasicBuff(double atk, double def, double hp, double crit, double crit_dmg, double satiety, double mood, double stamina, double primate) {
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.crit = crit;
        this.crit_dmg = crit_dmg;
        this.satiety = satiety;
        this.mood = mood;
        this.stamina = stamina;
        this.primate = primate;
    }

    /**
     * 클래스의 필드를 순회하며 입력받은 옵션을 능력치로 합산한다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            specDTO.add(field.getName(), (double) field.get(this));
        }
    }

}