package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import com.baebug.eht.manager.domain.item.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hunter {

    @Id
    @GeneratedValue
    @Column(name = "hunter_id")
    private Long id;

    private String name;
    private int fury;
    private int quicken;

    @Transient
    private SpecDto spec = new SpecDto();

    @Enumerated(EnumType.STRING)
    private Characteristic characteristic;    // Enum type

    @Enumerated(EnumType.STRING)
    private HunterClass hunterClass;        // Enum type

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id")
    private StatEntity stat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tech_id")
    private TechEntity tech = new TechEntity();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id")
    private final Equipment equipment = new Equipment();

    public Hunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) throws IllegalAccessException {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;

        this.calculate();
    }

    public void setTech(TechEntity tech) throws IllegalAccessException {
        this.tech = tech;
        this.calculate();
    }

    public void setFury(int fury) throws IllegalAccessException {
        this.fury = fury;
        this.calculate();
    }

    public void setQuicken(int quicken) throws IllegalAccessException {
        this.quicken = quicken;
        this.calculate();
    }

    //== 생성 메서드 ==//
    public static Hunter createHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) throws IllegalAccessException {
        return new Hunter(name, characteristic, hunterClass, stat);
    }

    //== 비즈니스 로작? ==//
    public void changeHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) throws IllegalAccessException {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;

        this.calculate();
    }

    public void setEquipment(Weapon weapon, Armor helmet, Armor chest, Armor glove, Armor shoes, Accessory necklace, Accessory ring, Accessory belt) throws IllegalAccessException {
        this.getEquipment().setEquipment(weapon, helmet, chest, glove, shoes, necklace, ring, belt);
        this.calculate();
    }

    public void calculate() throws IllegalAccessException {
        SpecDto tmp = new SpecDto();

        getStat().calculate(tmp);
        getTech().calculate(tmp);
        getCharacteristic().calculate(tmp);

        getEquipment().calculate(tmp);

        this.spec = tmp;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        getStat().calculate(specDto);
        getTech().calculate(specDto);
        getCharacteristic().calculate(specDto);

        getEquipment().calculate(specDto);

        this.spec = specDto;
    }

    public double getAtkSpd() {
        double atkSpd = getSpec().getAtk_spd();
        double spdRate = getSpec().getSpd() * .01;
        double furyRate = calcFury(getFury());
        double quickenRate = getQuicken() * .1;

        return atkSpd * (1 - spdRate) / (furyRate + quickenRate);
    }

    public double getAtkSpd(double expected) {
        double atkSpd = getSpec().getAtk_spd();
        double actualSpd = getSpec().getSpd();
        double furyRate = calcFury(getFury());
        double quickenRate = getQuicken() * .1;

        return 100 * (1 - (expected * (furyRate + quickenRate) / atkSpd)) - actualSpd;
    }

    private double calcFury(int n) {
        switch (n) {
            case 1:
                return 2.38;
            case 10:
                return 4;
            default:
                return 1;
        }
    }

}
