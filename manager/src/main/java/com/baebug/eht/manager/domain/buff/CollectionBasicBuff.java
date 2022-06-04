package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionBasicBuff {

    private double atk;
    private double def;
    private double hp;
    private double crit;
    private double crit_dmg;
    private double satiety;
    private double mood;
    private double stamina;
    private double primate;

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

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            specDto.add(field.getName(), (double) field.get(this));
        }
    }

}
