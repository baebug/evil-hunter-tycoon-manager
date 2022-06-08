package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.HunterDTO;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hunter_id")
    private Long id;

    private String name;
    private String desc;

    @Enumerated(EnumType.STRING)
    private Characteristic characteristic;    // Enum type

    @Embedded
    private HunterClass hunterClass;        // Enum type

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stat_id")
    private StatEntity stat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tech_id")
    private TechEntity tech = new TechEntity();

    @Embedded
    private final Equipment equipment = new Equipment();

    /**
     * 이름, 성격, 직업, 스탯, 메모를 입력받아 Hunter 객체의 인스턴스를 생성하고 능력치를 합산한다.
     */
    public Hunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat, String desc) throws IllegalAccessException {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
        this.desc = desc;
    }

    /**
     * Tech 객체를 입력받는 메서드
     * 변경사항이 생기면 능력치를 새로 합산한다.
     */
    public void setTech(TechEntity tech) {
        this.tech = tech;
    }

    /**
     * 생성 메서드
     */
    public static Hunter createHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat, String desc) throws IllegalAccessException {
        return new Hunter(name, characteristic, hunterClass, stat, desc);
    }

    /**
     * 헌터 정보를 수정하는 메서드
     */
    public void changeHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat, String desc) throws IllegalAccessException {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
        this.desc = desc;
    }

    /**
     * 장비로 오른 능력치를 반환한다.
     */
    public SpecDTO getEquipmentSpec() throws IllegalAccessException {
        return getEquipment().calculate();
    }

    public SpecDTO getTechSpec() throws IllegalAccessException {
        return getTech().calculate();
    }

    public SpecDTO getStatSpec() throws IllegalAccessException {
        return getStat().calculate();
    }

    public SpecDTO getCharacteristicSpec() throws IllegalAccessException {
        return getCharacteristic().calculate();
    }

    /**
     * specDTO 를 초기화 후 종합 능력치를 합산한다.
     * 합산 대상: 성격, 스탯, 비법, 장비
     */
    public SpecDTO getTotalSpec() throws IllegalAccessException {
        SpecDTO specDTO = new SpecDTO();

        specDTO.add(getCharacteristic().calculate());
        specDTO.add(getStat().calculate());
        specDTO.add(getTech().calculate());
        specDTO.add(getEquipment().calculate());

        return specDTO;
    }

    /**
     * 현재 공격속도를 계산하여 반환한다.
     */
    public double getAtkSpd(SpecDTO totalSpec) throws IllegalAccessException {
        double atkSpd = totalSpec.getAtk_spd();
        double spdRate = totalSpec.getSpd() * .01;
        double furyRate = calcFury(getTech().getFury());
        double quickenRate = getTech().getQuicken() * .1;

        return atkSpd * (1 - spdRate) / (furyRate + quickenRate);
    }

    /**
     * 목표 공격속도를 입력받아, 필요 공격속도를 계산하여 반환한다.
     * @param expected      목표 공격속도
     */
    public double getAtkSpd(SpecDTO totalSpec, double expected) throws IllegalAccessException {
        double atkSpd = totalSpec.getAtk_spd();
        double actualSpd = totalSpec.getSpd();
        double furyRate = calcFury(getTech().getFury());
        double quickenRate = getTech().getQuicken() * .1;

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
