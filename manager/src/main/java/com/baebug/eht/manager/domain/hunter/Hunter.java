package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import com.baebug.eht.manager.domain.item.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hunter {

    @Id @GeneratedValue
    @Column(name = "hunter_id")
    private Long id;

    private String name;
    private int fury;
    private int quicken;

    @Enumerated(EnumType.STRING)
    private Characteristic characteristic;    // Enum type

    @Enumerated(EnumType.STRING)
    private HunterClass hunterClass;        // Enum type

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id")
    private StatEntity stat;

    @OneToMany(mappedBy = "hunter", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tech_id")
    private TechEntity tech = new TechEntity();

    public Hunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
    }

    public void setTech(TechEntity tech) {
        this.tech = tech;
    }

    public void setFury(int fury) {
        this.fury = fury;
    }

    public void setQuicken(int quicken) {
        this.quicken = quicken;
    }

    //== 연관관계 편의 메서드 ==//
    public void setItem(Item item) {
        getItems().add(item);
        item.setHunter(this);
    }

    //== 생성 메서드 ==//
    public static Hunter createHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) {
        return new Hunter(name, characteristic, hunterClass, stat);
    }

    //== 비즈니스 로작? ==//
    public void changeHunter(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
    }

    public SpecDto calculate(SpecDto specDto) throws IllegalAccessException {
        getStat().calculate(specDto);
        getTech().calculate(specDto);
        getCharacteristic().calculate(specDto);

        for (Item item : getItems()) {
            item.calculate(specDto);
        }

        return specDto;
    }

}
