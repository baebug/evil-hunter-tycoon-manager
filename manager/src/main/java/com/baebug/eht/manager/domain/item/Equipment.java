package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * Equipment 객체
 * 모든 종류의 장비를 필드로 가지고 있다.
 */
@Embeddable
@Getter
public class Equipment {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weapon_id")
    private Weapon weapon = new Weapon();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "helmet_id")
    private Helmet helmet = new Helmet();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "armor_id")
    private Armor armor = new Armor();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "glove_id")
    private Glove glove = new Glove();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoes_id")
    private Shoes shoes = new Shoes();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "necklace_id")
    private Necklace necklace = new Necklace();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ring_id")
    private Ring ring = new Ring();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "belt_id")
    private Belt belt = new Belt();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rune_id")
    private Rune rune = new Rune();

    /**
     * 입력받은 specDTO 에 착용중인 장비의 능력치를 합산한다.
     * Equipment 클래스의 모든 필드를 순회하며 calculate 메서드를 호출한다.
     */
    public SpecDTO calculate() throws IllegalAccessException {
        SpecDTO specDTO = new SpecDTO();

        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Item item = (Item) field.get(this);
            item.calculate(specDTO);
        }
        return specDTO;
    }
}
