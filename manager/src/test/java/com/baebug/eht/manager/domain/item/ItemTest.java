package com.baebug.eht.manager.domain.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.baebug.eht.manager.domain.item.OptionList.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Item domain Test")
class ItemTest {

    @Test
    @DisplayName("장비 옵션 변경")
    public void update() throws Exception {
        // given
        Item item1 = Item.createItem(Arrays.asList(createItemOptionEntity(ATK, 10), createItemOptionEntity(DEF, 12)));

        // when
        item1.changeOption(Arrays.asList(createItemOptionEntity(CRIT, 13), createItemOptionEntity(HP, 21), createItemOptionEntity(SPD, 8)));

        // then
        for (ItemOptionEntity itemOption : item1.getItemOptions()) {
            System.out.println("itemOption = " + itemOption.getItemOption().getOption());
            System.out.println("itemValue = " + itemOption.getItemOption().getValue());
        }
        assertEquals(3, item1.getItemOptions().size());
    }

    private ItemOptionEntity createItemOptionEntity(OptionList option, int value) {
        ItemOption itemOption = new ItemOption(option, value);
        ItemOptionEntity itemOptionEntity = new ItemOptionEntity();
        itemOptionEntity.setItemOption(itemOption);

        return itemOptionEntity;
    }

}