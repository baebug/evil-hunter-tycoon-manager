package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import com.baebug.eht.manager.domain.item.Item;
import com.baebug.eht.manager.domain.item.ItemOption;
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
        Item item = Item.createItem(Arrays.asList(createItemOption(ATK, 10), createItemOption(DEF, 12)));

        // when
        hunter.setItem(item);

        // then
        assertEquals(1, hunter.getItems().size());
        assertEquals(hunter, item.getHunter());
    }

    @Test
    @DisplayName("specDto 의 add, print 메서드 테스트")
    public void specDtoTest() throws Exception {
        // given
        SpecDto specDto = new SpecDto();

        // when
        specDto.add("atk", 10);

        // then
        specDto.print();
    }

    @Test
    @DisplayName("헌터의 전체 능력치 출력 테스트")
    public void specTest() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.Strong, HunterClass.BERSERKER, new Stat(1, 2, 3, 4, 1, 2, 3, 4, 1));
        Item item1 = Item.createItem(Arrays.asList(createItemOption(ATK, 10), createItemOption(DEF, 12)));
        Item item2 = Item.createItem(Arrays.asList(createItemOption(HP, 20), createItemOption(CRIT, 21), createItemOption(CRIT_DMG, 23)));
        Tech tech1 = createTech(TechList.ATK, 100);
        Tech tech2 = createTech(TechList.EVASION, 90);

        // when
        hunter.setItem(item1);
        hunter.setItem(item2);
        hunter.addTech(tech1);
        hunter.addTech(tech2);

        SpecDto specDto = hunter.calculate(new SpecDto());

        // then
        specDto.print();
    }

    @Test
    @DisplayName("헌터 등급 테스트")
    public void getGradeTest() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.Strong, HunterClass.BERSERKER, new Stat(0, 0, 0, 0, 0, 0, 0, 0, 0));
        Hunter hunter2 = new Hunter("헌터B", Characteristic.Strong, HunterClass.BERSERKER, new Stat(3, 3, 3, 3, 3, 3, 3, 3, 3));

        // then
        assertEquals("Normal", hunter1.getStat().getGrade());
        assertEquals("Ultimate+", hunter2.getStat().getGrade());
    }

    private ItemOption createItemOption(OptionList option, Integer value) {
        return new ItemOption(option, value);

    }

    private Tech createTech(TechList option, Integer value) {
        return new Tech(option, value);
    }

}
