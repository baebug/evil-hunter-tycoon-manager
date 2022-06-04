package com.baebug.eht.manager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 장신구 객체 (목걸이, 반지, 벨트)
 * 추후 체력 옵션의 추가를 위해 카테고리를 분류했다.
 */
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
