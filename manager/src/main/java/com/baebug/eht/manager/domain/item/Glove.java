package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Glove extends Item {

    @OneToOne(mappedBy = "glove", fetch = FetchType.LAZY)
    private Hunter hunter;

    //== 생성 메서드 ==//
    public static Item createGlove(ItemOptionEntity... itemOptions) {
        Glove item = new Glove();
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
