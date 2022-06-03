package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import com.baebug.eht.manager.domain.item.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;
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

    @Enumerated(EnumType.STRING)
    private Characteristic characteristic;    // Enum type

    @Enumerated(EnumType.STRING)
    private HunterClass hunterClass;        // Enum type

    @Embedded
    private Stat stat;                      // Embedded type

    @OneToMany(mappedBy = "hunter", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "hunter", cascade = CascadeType.ALL)
    private List<Tech> techs = new ArrayList<>();

    public Hunter(String name, Characteristic characteristic, HunterClass hunterClass, Stat stat) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
    }

    //== 연관관계 편의 메서드 ==//
    public void setItem(Item item) {
        getItems().add(item);
        item.setHunter(this);
    }

    public void addTech(Tech tech) {
        getTechs().add(tech);
        tech.setHunter(this);
    }

    //== 생성 메서드 ==//
    public static Hunter createHunter(String name, Characteristic characteristic, HunterClass hunterClass, Stat stat) {
        return new Hunter(name, characteristic, hunterClass, stat);
    }

    //== 비즈니스 로작? ==//
    public void changeHunter(String name, Characteristic characteristic, HunterClass hunterClass, Stat stat) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
    }

    public SpecDto calculate(SpecDto specDto) throws IllegalAccessException {
        for (Field statField : getStat().getClass().getDeclaredFields()) {
            statField.setAccessible(true);
            specDto.add(statField.getName(), (Integer) statField.get(getStat()));
        }

        for (Item item : getItems()) {
            item.calculate(specDto);
        }

        for (Tech tech : getTechs()) {
            tech.calculate(specDto);
        }

        specDto.add(characteristic.getOption(), characteristic.getValue());

        return specDto;
    }

}
