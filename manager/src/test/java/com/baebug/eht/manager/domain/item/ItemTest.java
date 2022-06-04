package com.baebug.eht.manager.domain.item;

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
        Item item1 = Accessory.createItem(Arrays.asList(createItemOption(ATK, 10), createItemOption(DEF, 12)));

        // when
        item1.changeOption(Arrays.asList(createItemOption(CRIT, 13), createItemOption(HP, 21), createItemOption(SPD, 8)));

        // then
        for (ItemOption itemOption : item1.getItemOptions()) {
            System.out.println("itemOption = " + itemOption.getOption());
            System.out.println("itemValue = " + itemOption.getValue());
        }
        assertEquals(3, item1.getItemOptions().size());
    }

    private ItemOption createItemOption(OptionList option, Integer value) {
        return new ItemOption(option, value);
    }

}