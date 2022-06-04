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
public class CollectionSetBuff {

    private int atk;
    private int def;
    private int hp;
    private int crit;
    private int crit_dmg;
    private int boss;
    private int primate;
    private int animal;
    private int demon;
    private int undead;

    public void setCollectionSetBuff(int atk, int def, int hp, int crit, int crit_dmg, int boss, int primate, int animal, int demon, int undead) {
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

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Double d = Double.valueOf(String.valueOf(field.get(this)));
            specDto.add(field.getName(), d);
        }
    }

}
