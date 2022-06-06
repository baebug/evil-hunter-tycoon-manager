package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import com.baebug.eht.manager.domain.dto.StatDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * 스탯 객체
 */
@Entity
@Table(name = "stat")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatEntity {

    @Id @GeneratedValue
    @Column(name = "stat_id")
    private Long id;

    /**
     * White, Gray  : 0
     * Blue         : 1
     * Orange       : 2
     * Purple       : 3
     */
    private int atk;
    private int def;
    private int crit;
    private int spd;
    private int evasion;
    private int hp;
    private int satiety;
    private int mood;
    private int stamina;

    /**
     * statDTO 를 입력받아 필드를 초기화한다.
     * @param statDTO       DTO
     */
    public StatEntity(StatDTO statDTO) {
        this.atk = statDTO.getAtk();
        this.def = statDTO.getDef();
        this.crit = statDTO.getCrit();
        this.spd = statDTO.getSpd();
        this.evasion = statDTO.getEvasion();
        this.hp = statDTO.getHp();
        this.satiety = statDTO.getSatiety();
        this.mood = statDTO.getMood();
        this.stamina = statDTO.getStamina();
    }

    /**
     * 클래스의 필드를 순회하며 입력받은 옵션을 능력치로 합산한다.
     * StatList 를 통해 옵션별 가중치를 적용한다.
     * Integer 를 Double 로 casting 하기 위해 String 타입으로 감싸준다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                double weight = StatList.getWeight(field.getName());
                specDTO.add(field.getName(), (Integer) field.get(this) * weight);
            }
        }
    }

    /**
     * 스탯을 합산한 뒤 결과값에 따라 등급을 반환한다.
     */
    public String getGrade() {
        int statSum = getAtk() + getDef() + getCrit() + getSpd() + getEvasion() + getHp() + getSatiety() + getMood() + getStamina();

        switch (statSum) {
            case 0: case 1:
                return "Normal";
            case 2: case 3:
                return "Normal+";
            case 4: case 5:
                return "Rare";
            case 6: case 7:
                return "Rare+";
            case 8: case 9:
                return "Superior";
            case 10: case 11:
                return "Superior+";
            case 12: case 13:
                return "Heroic";
            case 14: case 15:
                return "Heroic+";
            case 16: case 17:
                return "Legendary";
            case 18: case 19:
                return "Legendary+";
            case 20: case 21:
                return "Ultimate";
            default:
                return "Ultimate+";
        }
    }
}
