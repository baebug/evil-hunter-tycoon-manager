package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Helmet extends Item {

    @OneToOne(mappedBy = "helmet", fetch = FetchType.LAZY)
    private Hunter hunter;

    //== 생성 메서드 ==//
    public static Item createHelmet(ItemOptionEntity... itemOptions) {
        Helmet item = new Helmet();
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
