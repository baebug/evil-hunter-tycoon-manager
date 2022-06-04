package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDto;
import com.baebug.eht.manager.domain.item.Item;
import com.baebug.eht.manager.domain.item.ItemOption;
import com.baebug.eht.manager.domain.item.OptionList;
import com.baebug.eht.manager.domain.item.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;

import static com.baebug.eht.manager.domain.item.OptionList.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hunter domain Test")
class HunterTest {

    @Test
    @DisplayName("헌터 정보 변경")
    public void update() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, HunterClass.BERSERKER, new StatEntity());
        Hunter hunter2 = new Hunter("헌터B", Characteristic.SWIFT, HunterClass.SORCERER, new StatEntity());

        // when
        hunter1.changeHunter(hunter2.getName(), hunter2.getCharacteristic(), hunter2.getHunterClass(), hunter2.getStat());

        // then
        assertEquals(hunter2.getName(), hunter1.getName());
        assertEquals(hunter2.getCharacteristic(), hunter1.getCharacteristic());
        assertEquals(hunter2.getHunterClass(), hunter1.getHunterClass());
        assertEquals(hunter2.getStat(), hunter1.getStat());
    }

    @Test
    @DisplayName("장비 능력치 적용 테스트")
    public void setItem() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.OTHERS, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));
        Item item1 = Item.createItem(Arrays.asList(createItemOption(ATK, 10), createItemOption(DEF, 12)));
        Item item2 = Weapon.createItem(2.3, Arrays.asList(createItemOption(ATK, 10), createItemOption(CRIT, 21)));

        // when
        hunter.setItem(item1);
        hunter.setItem(item2);
        SpecDto result = hunter.calculate(new SpecDto());

        // then
        assertEquals(2, hunter.getItems().size());
        assertEquals(hunter, item1.getHunter());
        assertEquals(20, result.getAtk());
        assertEquals(12, result.getDef());
        assertEquals(21, result.getCrit());
        assertEquals(2.3, result.getAtk_spd());
    }

    @Test
    @DisplayName("성격 능력치 적용 테스트")
    public void setCharacteristic() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));
        Hunter hunter2 = new Hunter("헌터B", Characteristic.SWIFT, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));

        // when
        SpecDto result1 = hunter1.calculate(new SpecDto());
        SpecDto result2 = hunter2.calculate(new SpecDto());

        // then
        assertEquals(10, result1.getAtk());
        assertEquals(10, result2.getSpd());
    }

    @Test
    @DisplayName("스탯 능력치 적용 테스트")
    public void setStat() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.OTHERS, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));
        Hunter hunter2 = new Hunter("헌터B", Characteristic.OTHERS, HunterClass.BERSERKER, new StatEntity(1, 2, 3, 1, 2, 3, 1, 2, 3));

        // when
        SpecDto result1 = hunter1.calculate(new SpecDto());
        SpecDto result2 = hunter2.calculate(new SpecDto());

        // then
        assertEquals(0, result1.getAtk());
        assertEquals(0, result1.getCrit());
        assertEquals(0, result1.getEvasion());

        assertEquals(10, result2.getAtk());
        assertEquals(6, result2.getCrit());
        assertEquals(4, result2.getEvasion());
    }

    @Test
    @DisplayName("specDto 의 add 메서드 테스트")
    public void specDtoTest() throws Exception {
        // given
        SpecDto specDto = new SpecDto();

        // when
        specDto.add("atk", 10.0);

        // then
        assertEquals(10, specDto.getAtk());
    }

    @Test
    @DisplayName("비법 능력치 적용 테스트")
    public void specTest() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.CHARISMATIC, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));
        TechEntity tech = new TechEntity(100, 10, 10, 10, 50, 0, 100, 50, 0, 10, 10, 10, 50, 100, 10);

        // when
        hunter.setTech(tech);

        SpecDto result = hunter.calculate(new SpecDto());

        // then
        assertEquals(15, result.getHp());
        assertEquals(7.5, result.getAtk());
        assertEquals(0, result.getDef());

        assertEquals(10, result.getCrit());
        assertEquals(5, result.getSpd());
        assertEquals(0, result.getEvasion());

        assertEquals(30, result.getSatiety());
        assertEquals(15, result.getMood());
    }

    @Test
    @DisplayName("헌터 등급 테스트")
    public void getGradeTest() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));
        Hunter hunter2 = new Hunter("헌터B", Characteristic.STRONG, HunterClass.BERSERKER, new StatEntity(3, 3, 3, 3, 3, 3, 3, 3, 3));

        // then
        assertEquals("Normal", hunter1.getStat().getGrade());
        assertEquals("Ultimate+", hunter2.getStat().getGrade());
    }

    private ItemOption createItemOption(OptionList option, Integer value) {
        return new ItemOption(option, value);

    }

}
