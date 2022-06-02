package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Hunter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Weapon extends Item {

    private Long atk_spd;

    @OneToOne(mappedBy = "weapon", fetch = FetchType.LAZY)
    private Hunter hunter;

    //== 생성 메서드 ==//
    public static Item createWeapon(Long atk_spd, ItemOptionEntity... itemOptions) {
        Weapon item = new Weapon();
        item.setAtk_spd(atk_spd);
        for (ItemOptionEntity itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }
}
