package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.HunterClassDTO;
import com.baebug.eht.manager.domain.dto.ItemDTO;
import com.baebug.eht.manager.domain.dto.StatDTO;
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
        Item item1 = new Item();

        // when

        // then
    }

    @Test
    @DisplayName("헌터 생성 시 장비 생성 테스트")
    public void autoCreateItem() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.CHARISMATIC, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");



        // then

    }

    private ItemOption createItemOption() {
        return new ItemOption(null, 0);
    }

    private ItemOption createItemOption(OptionList option, Integer value) {
        return new ItemOption(option, value);
    }

    private ItemDTO createItemDTO(ItemOption option1, ItemOption option2, ItemOption option3, ItemOption option4, ItemOption option5, ItemOption option6, ItemOption option7, ItemOption option8) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setOption1(option1);
        itemDTO.setOption2(option2);
        itemDTO.setOption3(option3);
        itemDTO.setOption4(option4);
        itemDTO.setOption5(option5);
        itemDTO.setOption6(option6);
        itemDTO.setOption7(option7);
        itemDTO.setOption8(option8);

        return itemDTO;
    }

    private StatDTO createStatDto(int atk, int def, int crit, int spd, int evasion, int hp, int satiety, int mood, int stamina) {
        StatDTO statDTO = new StatDTO();
        statDTO.setAtk(atk);
        statDTO.setDef(def);
        statDTO.setCrit(crit);
        statDTO.setSpd(spd);
        statDTO.setEvasion(evasion);
        statDTO.setHp(hp);
        statDTO.setSatiety(satiety);
        statDTO.setMood(mood);
        statDTO.setStamina(stamina);

        return statDTO;
    }

}