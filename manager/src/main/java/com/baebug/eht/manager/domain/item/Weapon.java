package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Weapon extends Item {

    private Double atk_spd;

    //== 생성 메서드 ==//
    public static Weapon createItem(Double atk_spd, List<ItemOption> itemOptions) {
        Weapon item = new Weapon();
        item.setAtk_spd(atk_spd);
        for (ItemOption itemOption : itemOptions) {
            item.addItemOption(itemOption);
        }

        return item;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        specDto.add("atk_spd", getAtk_spd());

        for (ItemOption itemOption : getItemOptions()) {
            specDto.add(itemOption.getOption().getOption(), (double) itemOption.getValue());
        }
    }
}
