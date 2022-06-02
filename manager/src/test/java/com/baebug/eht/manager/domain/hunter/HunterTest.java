package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.item.Item;
import com.baebug.eht.manager.domain.item.ItemOption;
import com.baebug.eht.manager.domain.item.ItemOptionEntity;
import com.baebug.eht.manager.domain.item.OptionList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.baebug.eht.manager.domain.item.OptionList.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hunter domain Test")
class HunterTest {

    @Test
    @DisplayName("헌터 정보 변경")
    public void update() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.Strong, HunterClass.BERSERKER, new Stat());
        Hunter hunter2 = new Hunter("헌터B", Characteristic.Swift, HunterClass.SORCERER, new Stat());

        // when
        hunter1.changeHunter(hunter2.getName(), hunter2.getCharacteristic(), hunter2.getHunterClass(), hunter2.getStat());

        // then
        assertEquals(hunter2.getName(), hunter1.getName());
        assertEquals(hunter2.getCharacteristic(), hunter1.getCharacteristic());
        assertEquals(hunter2.getHunterClass(), hunter1.getHunterClass());
        assertEquals(hunter2.getStat(), hunter1.getStat());
    }

    @Test
    @DisplayName("헌터에게 장비 추가")
    public void setItem() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.Strong, HunterClass.BERSERKER, new Stat());
        Item item = Item.createItem(Arrays.asList(createItemOptionEntity(ATK, 10), createItemOptionEntity(DEF, 12)));

        // when
        hunter.setItem(item);

        // then
        assertEquals(1, hunter.getItems().size());
        assertEquals(hunter, item.getHunter());
    }

    private ItemOptionEntity createItemOptionEntity(OptionList option, int value) {
        ItemOption itemOption = new ItemOption(option, value);

        return new ItemOptionEntity(itemOption);
    }

}