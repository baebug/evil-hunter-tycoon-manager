package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * Equipment 객체
 * 모든 종류의 장비를 필드로 가지고 있다.
 */
@Entity
@Getter
public class Equipment {

    @Id @GeneratedValue
    @Column(name = "equipment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "weapon_id")
    private Weapon weapon = new Weapon();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "helmet_id")
    private Helmet helmet = new Helmet();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "armor_id")
    private Armor armor = new Armor();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "glove_id")
    private Glove glove = new Glove();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoes_id")
    private Shoes shoes = new Shoes();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "necklace_id")
    private Necklace necklace = new Necklace();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ring_id")
    private Ring ring = new Ring();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "belt_id")
    private Belt belt = new Belt();

    /* 이것도 서비스에서 해야 할 일 같아서 일단 주석처리
    public void setEquipment(Weapon weapon, Helmet helmet, Armor armor, Glove glove, Shoes shoes, Necklace necklace, Ring ring, Belt belt) {
        this.weapon = weapon;
        this.helmet = helmet;
        this.armor = armor;
        this.glove = glove;
        this.shoes = shoes;
        this.necklace = necklace;
        this.ring = ring;
        this.belt = belt;
    }
    */

    /**
     * 클래스의 필드를 순회하며 Item 클래스에 있는 calculate 메서드를 호출한다.
     * reflection 을 통해 클래스를 찾고 invoke() 로 메서드를 실행하려 했으나 실패
     * Item 타입으로 casting 하는 방식을 사용했다.
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals("id") && field.get(this) != null) {
                Item item = (Item) field.get(this);
                item.calculate(specDTO);
            }
        }
    }
}