package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.HunterClassDTO;
import com.baebug.eht.manager.domain.dto.HunterDTO;
import com.baebug.eht.manager.domain.dto.SpecDTO;
import com.baebug.eht.manager.domain.dto.StatDTO;
import com.baebug.eht.manager.domain.item.*;
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
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(), new StatEntity(), "");
        HunterDTO hunterDTO = new HunterDTO();
        HunterClassDTO hunterClassDTO = new HunterClassDTO();
        StatDTO statDTO = createStatDto(0,0,0,0,0,0,0,0,0);
        hunterDTO.setName("hunter");
        hunterDTO.setCharacteristic(Characteristic.OTHERS);
        hunterDTO.setHunterClass(hunterClassDTO);
        hunterDTO.setDesc("check");
        hunterDTO.setStat(statDTO);

        // when
        hunter1.changeHunter(hunterDTO.getName(), hunterDTO.getCharacteristic(), new HunterClass(hunterClassDTO), new StatEntity(hunterDTO.getStat()), hunterDTO.getDesc());

        // then
        assertEquals(hunterDTO.getName(), hunter1.getName());
        assertEquals(hunterDTO.getCharacteristic(), hunter1.getCharacteristic());
        assertEquals(hunterDTO.getHunterClass(), hunter1.getHunterClass());
    }

    @Test
    @DisplayName("장비 능력치 적용 테스트")
    public void setItem() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");

        // when
        Weapon weapon = hunter.getEquipment().getWeapon();
        Necklace necklace = hunter.getEquipment().getNecklace();
        Shoes shoes = hunter.getEquipment().getShoes();

        weapon.setAtk_spd(2.3);
        weapon.changeOption(Arrays.asList(createItemOption(ATK, 10), createItemOption(CRIT, 21)));
        necklace.changeOption(Arrays.asList(createItemOption(ATK, 10), createItemOption(DEF, 12)));
        shoes.changeOption(Arrays.asList(createItemOption(EVASION, 10), createItemOption(CRIT_DMG, 12)));
        hunter.calculate();


        // then
        assertEquals(20, hunter.getSpec().getAtk());
        assertEquals(12, hunter.getSpec().getDef());
        assertEquals(21, hunter.getSpec().getCrit());
        assertEquals(2.3, hunter.getSpec().getAtk_spd());
    }

    @Test
    @DisplayName("성격 능력치 적용 테스트")
    public void setCharacteristic() throws Exception {
        // given
Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");
        Hunter hunter2 = new Hunter("헌터B", Characteristic.SWIFT, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");


        // when
        hunter1.calculate();
        hunter2.calculate();


        // then
        assertEquals(10, hunter1.getSpec().getAtk());
        assertEquals(10, hunter2.getSpec().getSpd());
    }

    @Test
    @DisplayName("스탯 능력치 적용 테스트")
    public void setStat() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");
        Hunter hunter2 = new Hunter("헌터B", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(1,0,3,0,2,0,0,0,0)), "");


        // when
        hunter1.calculate();
        hunter2.calculate();

        // then
        assertEquals(0, hunter1.getSpec().getAtk());
        assertEquals(0, hunter1.getSpec().getCrit());
        assertEquals(0, hunter1.getSpec().getEvasion());

        assertEquals(10, hunter2.getSpec().getAtk());
        assertEquals(6, hunter2.getSpec().getCrit());
        assertEquals(4, hunter2.getSpec().getEvasion());
    }

    @Test
    @DisplayName("specDto 의 add 메서드 테스트")
    public void specDtoTest() throws Exception {
        // given
        SpecDTO specDto = new SpecDTO();

        // when
        specDto.add("atk", 10.0);

        // then
        assertEquals(10, specDto.getAtk());
    }

    @Test
    @DisplayName("비법 능력치 적용 테스트")
    public void specTest() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.CHARISMATIC, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");

        TechEntity tech = new TechEntity(100, 10, 10, 10, 50, 0, 100, 50, 0, 10, 10, 10, 50, 100, 10);

        // when
        hunter.setTech(tech);
        hunter.calculate();

        // then
        assertEquals(15, hunter.getSpec().getHp());
        assertEquals(7.5, hunter.getSpec().getAtk());
        assertEquals(0, hunter.getSpec().getDef());

        assertEquals(10, hunter.getSpec().getCrit());
        assertEquals(5, hunter.getSpec().getSpd());
        assertEquals(0, hunter.getSpec().getEvasion());

        assertEquals(30, hunter.getSpec().getSatiety());
        assertEquals(15, hunter.getSpec().getMood());
    }

    @Test
    @DisplayName("헌터 등급 테스트")
    public void getGradeTest() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");


        // then
        assertEquals("Normal", hunter1.getStat().getGrade());
    }

    @Test
    @DisplayName("무기를 보유한 헌터의 공격속도 출력")
    public void getAtkSpdTest() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");
        Hunter hunter2 = new Hunter("헌터B", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");


        // when
        hunter1.getEquipment().getWeapon().setAtk_spd(1.5);
        hunter1.getEquipment().getWeapon().changeOption(Arrays.asList(createItemOption(SPD, 5)));
        hunter1.calculate();

        hunter2.getEquipment().getWeapon().setAtk_spd(1.5);
        hunter2.getEquipment().getWeapon().changeOption(Arrays.asList(createItemOption(SPD, 70)));
        hunter2.calculate();

        // then
        System.out.println("헌터A 의 현재 공격속도 = " + hunter1.getAtkSpd());
        System.out.println("헌터A 의 필요 공격속도 = " + hunter1.getAtkSpd(0.25));
        System.out.println("헌터B 의 현재 공격속도 = " + hunter2.getAtkSpd());
        System.out.println("헌터B 의 필요 공격속도 = " + hunter2.getAtkSpd(0.25));
    }

    @Test
    @DisplayName("무기를 보유하지 않은 헌터의 공격속도 출력")
    public void getAtkSpdTest2() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0,0)), "");


        // then
        System.out.println("헌터의 현재 공격속도 = " + hunter1.getAtkSpd());
        System.out.println("0.25 까지 도달을 위한 필요 공격속도 = " + hunter1.getAtkSpd(0.25));
    }

    private ItemOption createItemOption(OptionList option, Integer value) {
        return new ItemOption(option, value);

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
