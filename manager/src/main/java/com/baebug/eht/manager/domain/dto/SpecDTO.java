package com.baebug.eht.manager.domain.dto;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * 능력치 합산을 위해 사용하는 DTO
 */
@Data
public class SpecDTO {
    /**
     * 프로그램에 사용되는 모든 능력치를 필드로 가지고 있다.
     */
    private double atk_spd;

    private double atk;
    private double def;
    private double hp;
    private double evasion;
    private double spd;
    private double crit;
    private double crit_dmg;
    private double add_dmg;
    private double primate;
    private double demon;
    private double undead;
    private double animal;
    private double boss;
    private double dmg_taken;
    private double life_steal;
    private double dark_lord;
    private double dodge;
    private double stun;
    private double material;
    private double satiety;         // 소모량 감소
    private double mood;
    private double satiety_up;      // 회복
    private double mood_up;
    private double satiety_max;     // 최대치
    private double mood_max;
    private double stamina_max;

    /**
     * 클래스를 순회하며 입력받는 option 값과 일치하는 필드에 value 를 합산한다.
     * @param option        String 타입의 옵션 이름
     * @param value         double 타입의 옵션 값
     */
    public void add(String option, Double value) throws IllegalAccessException {
        Field[] specFields = getClass().getDeclaredFields();

        for (Field specField : specFields) {
            specField.setAccessible(true);
            if (option.equals(specField.getName())) {
                specField.set(this, (double) specField.get(this) + value);
            }
        }
    }

    public void clear() throws IllegalAccessException {
        Field[] specFields = getClass().getDeclaredFields();

        for (Field specField : specFields) {
            specField.setAccessible(true);
            specField.set(this, 0);
        }
    }

    /**
     * DTO 의 모든 필드를 출력한다.
     * Test 용도로 만든 메서드
     */
    public void print() throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + ", " + field.get(this));
        }
    }
}
