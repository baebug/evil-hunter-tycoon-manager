package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Shoes extends Item {

    @OneToOne(mappedBy = "shoes", fetch = FetchType.LAZY)
    private Hunter hunter;

    //== 생성 메서드 ==//
    public static Item createShoes(ItemOptionEntity... itemOptions) {
        Shoes item = new Shoes();
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
