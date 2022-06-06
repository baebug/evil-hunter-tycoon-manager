package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import com.baebug.eht.manager.domain.dto.TechDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
    private int mood_max;
    private int satiety_max;
    private int stamina_max;
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

    private int fury;
    private int quicken;

    public TechEntity(TechDTO techDTO) {
        this.hp = techDTO.getHp();
        this.mood_max = techDTO.getMood_max();
        this.satiety_max = techDTO.getSatiety_max();
        this.stamina_max = techDTO.getStamina_max();
        this.atk = techDTO.getAtk();
        this.def = techDTO.getDef();
        this.crit = techDTO.getCrit();
        this.spd = techDTO.getSpd();
        this.evasion = techDTO.getEvasion();
        this.walk = techDTO.getWalk();
        this.skill1 = techDTO.getSkill1();
        this.skill2 = techDTO.getSkill2();
        this.mood = techDTO.getMood();
        this.satiety = techDTO.getSatiety();
        this.stamina = techDTO.getStamina();

        this.quicken = techDTO.getQuicken();
        this.fury = techDTO.getFury();
    }

    /**
     * 클래스의 필드를 순회하며 입력받은 옵션을 능력치로 합산한다.
     * TechList 를 통해 옵션별 가중치를 적용한다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id") && !field.getName().equals("fury") && !field.getName().equals("quicken")) {
                double weight = TechList.getWeight(field.getName());
                specDTO.add(field.getName(), (Integer) field.get(this) * weight);
            }
        }
    }
}
