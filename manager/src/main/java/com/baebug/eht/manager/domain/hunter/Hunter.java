package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import com.baebug.eht.manager.domain.item.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Hunter 객체
 */
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
    private SpecDTO spec = new SpecDTO();

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

    /**
     * 이름, 성격, 직업, 스탯을 입력받아 Hunter 객체의 인스턴스를 생성하고 능력치를 합산한다.
     */
    public Hunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) throws IllegalAccessException {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;

        this.calculate();
    }

    /**
     * Tech 객체를 입력받는 메서드
     * 변경사항이 생기면 능력치를 새로 합산한다.
     */
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

    /**
     * 생성 메서드
     */
    public static Hunter createHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) throws IllegalAccessException {
        return new Hunter(name, characteristic, hunterClass, stat);
    }

    /**
     * 헌터 정보를 수정하는 메서드
     */
    public void changeHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) throws IllegalAccessException {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;

        this.calculate();
    }

    /**
     * 헌터 장비를 교체하는 메서드
     * 서비스에서 해야 할 일이라고 생각해서 일단 주석처리 하였다.
     */

    /*
    public void setEquipment(Weapon weapon, Helmet helmet, Armor armor, Glove glove, Shoes shoes, Necklace necklace, Ring ring, Belt belt) throws IllegalAccessException {
        this.getEquipment().setEquipment(weapon, helmet, armor, glove, shoes, necklace, ring, belt);
        this.calculate();
    }
    */

    /**
     * SpecDTO 인스턴스를 새로 생성하여 능력치를 합산 후 저장한다.
     */
    public void calculate() throws IllegalAccessException {
        SpecDTO tmp = new SpecDTO();

        getStat().calculate(tmp);
        getTech().calculate(tmp);
        getCharacteristic().calculate(tmp);

        getEquipment().calculate(tmp);

        this.spec = tmp;
    }

    /**
     * SpecDTO 인스턴스를 입력받아 능력치를 합산 후 저장한다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        getStat().calculate(specDTO);
        getTech().calculate(specDTO);
        getCharacteristic().calculate(specDTO);

        getEquipment().calculate(specDTO);

        this.spec = specDTO;
    }

    /**
     * 현재 공격속도를 계산하여 반환한다.
     */
    public double getAtkSpd() {
        double atkSpd = getSpec().getAtk_spd();
        double spdRate = getSpec().getSpd() * .01;
        double furyRate = calcFury(getFury());
        double quickenRate = getQuicken() * .1;

        return atkSpd * (1 - spdRate) / (furyRate + quickenRate);
    }

    /**
     * 목표 공격속도를 입력받아, 필요 공격속도를 계산하여 반환한다.
     * @param expected      목표 공격속도
     */
    public double getAtkSpd(double expected) {
        double atkSpd = getSpec().getAtk_spd();
        double actualSpd = getSpec().getSpd();
        double furyRate = calcFury(getFury());
        double quickenRate = getQuicken() * .1;

        return 100 * (1 - (expected * (furyRate + quickenRate) / atkSpd)) - actualSpd;
    }

    /**
     * Fury 레벨을 입력받아 계수를 반환한다.
     */
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
