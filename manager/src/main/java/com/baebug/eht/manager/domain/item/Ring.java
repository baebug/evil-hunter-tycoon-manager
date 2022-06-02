package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Ring extends Item {

    @OneToOne(mappedBy = "ring", fetch = FetchType.LAZY)
    private Hunter hunter;

    //== 생성 메서드 ==//
    public static Item createRing(ItemOptionEntity... itemOptions) {
        Ring item = new Ring();
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
