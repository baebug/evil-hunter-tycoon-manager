package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Armor extends Item {

    @OneToOne(mappedBy = "armor", fetch = FetchType.LAZY)
    private Hunter hunter;

    //== 연관관계 편의 메서드 ==//

    //== 생성 메서드 ==//
    public static Item createArmor(ItemOptionEntity... itemOptions) {
        Armor item = new Armor();
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
