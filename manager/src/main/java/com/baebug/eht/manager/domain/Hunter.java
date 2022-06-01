package com.baebug.eht.manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Hunter(String name, Characteristic characteristic, HunterClass hunterClass, Stat stat) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
    }

    public static Hunter createHunter(String name, Characteristic characteristic, HunterClass hunterClass, Stat stat) {
        return new Hunter(name, characteristic, hunterClass, stat);
    }

    public void changeHunter(String name, Characteristic characteristic, HunterClass hunterClass, Stat stat) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
    }
}
