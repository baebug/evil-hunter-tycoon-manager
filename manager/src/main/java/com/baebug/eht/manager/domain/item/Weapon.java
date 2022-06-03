package com.baebug.eht.manager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Weapon extends Item {

    private int atk_spd;

    //== 생성 메서드 ==//
    public static Item createItem(int atk_spd, ItemOption... itemOptions) {
        Weapon item = new Weapon();
        item.setAtk_spd(atk_spd);
        for (ItemOption itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
