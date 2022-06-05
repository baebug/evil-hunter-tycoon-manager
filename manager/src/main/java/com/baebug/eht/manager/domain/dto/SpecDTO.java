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
     * 초기값 0
     */
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
