package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 무기 객체
 * 무기 공격속도 옵션을 위해 카테고리를 분류했다.
 */
@Entity
@Getter @Setter
public class Weapon extends Item {

    /**
     * 무기 공격속도
     */
    private double atk_spd;

    public static Weapon createItem(double atk_spd, List<ItemOption> itemOptions) {
        Weapon item = new Weapon();
        item.setAtk_spd(atk_spd);
        for (ItemOption itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }
        return item;
    }

    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        specDTO.add("atk_spd", getAtk_spd());

        for (ItemOption itemOption : getItemOptions()) {
            specDTO.add(itemOption.getOption().getOption(), (double) itemOption.getValue());
        }
    }
}
