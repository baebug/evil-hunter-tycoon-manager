package com.baebug.eht.manager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 방어구 객체 (투구, 갑옷, 장갑, 신발)
 */
@Entity
@Getter @Setter
public class Armor extends Item {

    public static Armor createItem(List<ItemOption> itemOptions) {
        Armor item = new Armor();
        for (ItemOption itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
