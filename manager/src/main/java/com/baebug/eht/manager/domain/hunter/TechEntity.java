package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.Getter;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "tech")
@Getter
public class TechEntity {

    @Id @GeneratedValue
    @Column(name = "tech_id")
    private Long id;

    private int hp;
    private int max_mood;
    private int max_satiety;
    private int max_stamina;
    private int atk;
    private int def;
    private int crit;
    private int spd;
    private int evasion;
    private int walk;
    private int skill1;
    private int skill2;
    private int mood;
    private int satiety;
    private int stamina;

    protected TechEntity() {
        this.hp = 0;
        this.max_mood = 0;
        this.max_satiety = 0;
        this.max_stamina = 0;
        this.atk = 0;
        this.def = 0;
        this.crit = 0;
        this.spd = 0;
        this.evasion = 0;
        this.walk = 0;
        this.skill1 = 0;
        this.skill2 = 0;
        this.mood = 0;
        this.satiety = 0;
        this.stamina = 0;
    }

    public TechEntity(int hp, int max_mood, int max_satiety, int max_stamina, int atk, int def, int crit, int spd, int evasion, int walk, int skill1, int skill2, int mood, int satiety, int stamina) {
        this.hp = hp;
        this.max_mood = max_mood;
        this.max_satiety = max_satiety;
        this.max_stamina = max_stamina;
        this.atk = atk;
        this.def = def;
        this.crit = crit;
        this.spd = spd;
        this.evasion = evasion;
        this.walk = walk;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.mood = mood;
        this.satiety = satiety;
        this.stamina = stamina;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName() != "id") {
                double weight = TechList.getWeight(field.getName());
                specDto.add(field.getName(), (Integer) field.get(this) * weight);
            }
        }
    }
}
