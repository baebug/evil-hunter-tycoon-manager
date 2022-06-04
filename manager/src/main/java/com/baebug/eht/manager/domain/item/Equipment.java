package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Getter @Setter
public class Equipment {

    @Id @GeneratedValue
    @Column(name = "equipment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "helmet_id")
    private Armor helmet;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chest_id")
    private Armor chest;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "glove_id")
    private Armor glove;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoes_id")
    private Armor shoes;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "necklace_id")
    private Accessory necklace;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ring_id")
    private Accessory ring;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "belt_id")
    private Accessory belt;

    public void setEquipment(Weapon weapon, Armor helmet, Armor chest, Armor glove, Armor shoes, Accessory necklace, Accessory ring, Accessory belt) {
        this.weapon = weapon;
        this.helmet = helmet;
        this.chest = chest;
        this.glove = glove;
        this.shoes = shoes;
        this.necklace = necklace;
        this.ring = ring;
        this.belt = belt;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName() != "id" && field.get(this) != null) {
                Item item = (Item) field.get(this);
                item.calculate(specDto);
            }
        }

    }
}
