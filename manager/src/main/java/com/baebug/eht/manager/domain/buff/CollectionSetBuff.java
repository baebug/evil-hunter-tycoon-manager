package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 도감 세트 버프 클래스
 */
@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionSetBuff {

    /**
     * 도감 세트 수집으로 적용되는 버프 옵션
     * double type (int type 으로 해도 상관없지만, casing 의 편의를 위해 double 을 사용하였다.)
     */
    private double atk;
    private double def;
    private double hp;
    private double crit;
    private double crit_dmg;
    private double boss;
    private double primate;
    private double animal;
    private double demon;
    private double undead;

    /**
     * 도감 세트 버프를 입력받는 메서드
     */
    public void setCollectionSetBuff(double atk, double def, double hp, double crit, double crit_dmg, double boss, double primate, double animal, double demon, double undead) {
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.crit = crit;
        this.crit_dmg = crit_dmg;
        this.boss = boss;
        this.primate = primate;
        this.animal = animal;
        this.demon = demon;
        this.undead = undead;
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
