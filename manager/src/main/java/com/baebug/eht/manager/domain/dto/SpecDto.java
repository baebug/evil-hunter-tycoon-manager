package com.baebug.eht.manager.domain.dto;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class SpecDto {
    private double atk_spd = 0;

    private double atk = 0;
    private double def = 0;
    private double hp = 0;
    private double evasion = 0;
    private double spd = 0;
    private double crit = 0;
    private double crit_dmg = 0;
    private double add_dmg = 0;
    private double primate = 0;
    private double demon = 0;
    private double undead = 0;
    private double animal = 0;
    private double boss = 0;
    private double dmg_taken = 0;
    private double life_steal = 0;
    private double dark_lord = 0;
    private double dodge = 0;
    private double stun = 0;
    private double material = 0;
    private double satiety = 0;         // 소모량 감소
    private double mood = 0;
    private double satiety_up = 0;      // 회복
    private double mood_up = 0;
    private double satiety_max = 0;     // 최대치
    private double mood_max = 0;
    private double stamina_max = 0;

    public void add(String option, Double value) throws IllegalAccessException {
        Field[] specFields = getClass().getDeclaredFields();

        for (Field specField : specFields) {
            specField.setAccessible(true);
            if (option.equals(specField.getName())) {
                specField.set(this, (Double) specField.get(this) + value);
            }
        }
    }

    public void print() throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + ", " + field.get(this));
        }
    }
}
