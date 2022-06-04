package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "stat")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatEntity {

    @Id @GeneratedValue
    @Column(name = "stat_id")
    private Long id;

    private int atk;
    private int def;
    private int crit;
    private int spd;
    private int evasion;
    private int hp;
    private int satiety;
    private int mood;
    private int stamina;

    public StatEntity(int atk, int def, int crit, int spd, int evasion, int hp, int satiety, int mood, int stamina) {
        this.atk = atk;
        this.def = def;
        this.crit = crit;
        this.spd = spd;
        this.evasion = evasion;
        this.hp = hp;
        this.satiety = satiety;
        this.mood = mood;
        this.stamina = stamina;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName() != "id") {
                int weight = StatList.getWeight(field.getName());
                Double a = Double.valueOf(String.valueOf(field.get(this)));
                specDto.add(field.getName(), a * weight);
            }
        }
    }

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
