package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * 비법 객체
 */
@Entity
@Table(name = "tech")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechEntity {

    @Id @GeneratedValue
    @Column(name = "tech_id")
    private Long id;

    /**
     * 0 부터 100 까지의 비법 레벨
     */
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

    /**
     * 클래스의 필드를 순회하며 입력받은 옵션을 능력치로 합산한다.
     * TechList 를 통해 옵션별 가중치를 적용한다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                double weight = TechList.getWeight(field.getName());
                specDTO.add(field.getName(), (Integer) field.get(this) * weight);
            }
        }
    }
}
