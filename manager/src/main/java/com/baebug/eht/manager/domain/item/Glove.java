package com.baebug.eht.manager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter @Setter
public class Glove extends Gear {

    public static Glove createItem(List<ItemOption> itemOptions) {
        Glove item = new Glove();
        for (ItemOption itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }
        return item;
    }
}
