package com.baebug.eht.manager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Accessory extends Item {

    public static Accessory createItem(List<ItemOption> itemOptions) {
        Accessory item = new Accessory();
        for (ItemOption itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }

}
