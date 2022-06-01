package com.baebug.eht.manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

/**
 * Stat (ATK, DEF, CRIT, ATK_SPD, Evasion, Health, satiety, Mood, Stamina)
 */

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class Stat {
    private int atk;
    private int def;
    private int crit;
    private int atk_spd;
    private int evasion;
    private int hp;
    private int satiety;
    private int mood;
    private int stamina;

    public Stat(int atk, int def, int crit, int atk_spd, int evasion, int hp, int satiety, int mood, int stamina) {
        this.atk = atk;
        this.def = def;
        this.crit = crit;
        this.atk_spd = atk_spd;
        this.evasion = evasion;
        this.hp = hp;
        this.satiety = satiety;
        this.mood = mood;
        this.stamina = stamina;
    }

    public void getGrade() {
        int statSum = getAtk() + getDef() + getCrit() + getAtk_spd() + getEvasion() + getHp() + getSatiety() + getMood() + getStamina();

    }
}
