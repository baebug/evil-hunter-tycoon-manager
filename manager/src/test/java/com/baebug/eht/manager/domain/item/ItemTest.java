package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
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
        Item item1 = Item.createItem(Arrays.asList(createItemOption(ATK, 10), createItemOption(DEF, 12)));

        // when
        item1.changeOption(Arrays.asList(createItemOption(SPD, 20)));
        item1.changeOption(Arrays.asList(createItemOption(CRIT, 13), createItemOption(HP, 21), createItemOption(SPD, 8)));

        // then
        assertEquals(3, item1.getItemOptions().size());
    }

    @Test
    @DisplayName("헌터 생성 시 장비 생성 테스트")
    public void autoCreateItem() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.CHARISMATIC, HunterClass.PALADIN, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));

        hunter.getEquipment().getWeapon().getItemOptions().add(createItemOption(ATK, 10));

        // then
        assertEquals(1, hunter.getEquipment().getWeapon().getItemOptions().size());
        assertEquals(0, hunter.getEquipment().getWeapon().getAtk_spd());
        assertEquals(0, hunter.getEquipment().getBelt().getItemOptions().size());

    }

    private ItemOption createItemOption(OptionList option, Integer value) {
        return new ItemOption(option, value);
    }

}