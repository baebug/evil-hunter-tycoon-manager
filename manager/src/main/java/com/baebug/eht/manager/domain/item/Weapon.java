package com.baebug.eht.manager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Weapon extends Item {

    private Double atk_spd;

    //== 생성 메서드 ==//
    public static Item createItem(Double atk_spd, ItemOptionEntity... itemOptions) {
        Weapon item = new Weapon();
        item.setAtk_spd(atk_spd);
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
