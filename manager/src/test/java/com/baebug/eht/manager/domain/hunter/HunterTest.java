package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.*;
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
        StatDTO statDTO = createStatDTO(0,0,0,0,0,0,0,0,0);
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
    }

    @Test
    @DisplayName("장비 능력치 적용 테스트")
    public void setItem() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");

        // when
        Weapon weapon = hunter.getEquipment().getWeapon();

        ItemDTO itemDTO = createItemDTO(createItemOption(ATK, 10), createItemOption(CRIT, 21), createItemOption(), createItemOption(), createItemOption(), createItemOption(), createItemOption(), createItemOption());
        weapon.setAtk_spd(2.3);
        weapon.changeOption(itemDTO);
        hunter.setItemSpec();


        // then
        assertEquals(10, hunter.getItemSpec().getAtk());
        assertEquals(21, hunter.getItemSpec().getCrit());
        assertEquals(2.3, hunter.getItemSpec().getAtk_spd());
    }

    @Test
    @DisplayName("성격 능력치 적용 테스트")
    public void setCharacteristic() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");
        Hunter hunter2 = new Hunter("헌터B", Characteristic.SWIFT, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");


        // when
        hunter1.setTotalSpec();
        hunter2.setTotalSpec();


        // then
        assertEquals(10, hunter1.getTotalSpec().getAtk());
        assertEquals(10, hunter2.getTotalSpec().getSpd());
    }

    @Test
    @DisplayName("스탯 능력치 적용 테스트")
    public void setStat() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");
        Hunter hunter2 = new Hunter("헌터B", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(1,0,3,0,2,0,0,0,0)), "");


        // when
        hunter1.setTotalSpec();
        hunter2.setTotalSpec();

        // then
        assertEquals(0, hunter1.getTotalSpec().getAtk());
        assertEquals(0, hunter1.getTotalSpec().getCrit());
        assertEquals(0, hunter1.getTotalSpec().getEvasion());

        assertEquals(10, hunter2.getTotalSpec().getAtk());
        assertEquals(6, hunter2.getTotalSpec().getCrit());
        assertEquals(4, hunter2.getTotalSpec().getEvasion());
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
        Hunter hunter = new Hunter("헌터A", Characteristic.CHARISMATIC, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");

        TechEntity tech = new TechEntity(createTechDTO(100, 10, 10, 10, 50, 0, 100, 50, 0, 10, 10, 10, 50, 100, 10));

        // when
        hunter.setTech(tech);
        hunter.setTotalSpec();

        // then
        assertEquals(15, hunter.getTotalSpec().getHp());
        assertEquals(7.5, hunter.getTotalSpec().getAtk());
        assertEquals(0, hunter.getTotalSpec().getDef());

        assertEquals(10, hunter.getTotalSpec().getCrit());
        assertEquals(5, hunter.getTotalSpec().getSpd());
        assertEquals(0, hunter.getTotalSpec().getEvasion());

        assertEquals(30, hunter.getTotalSpec().getSatiety());
        assertEquals(15, hunter.getTotalSpec().getMood());
    }

    @Test
    @DisplayName("헌터 등급 테스트")
    public void getGradeTest() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");


        // then
        assertEquals("Normal", hunter1.getStat().getGrade());
    }

    @Test
    @DisplayName("무기를 보유한 헌터의 공격속도 출력")
    public void getAtkSpdTest() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");


        // when
        hunter1.getEquipment().getWeapon().setAtk_spd(1.8);
        hunter1.setTotalSpec();


        // then
        System.out.println("헌터A 의 현재 공격속도 = " + hunter1.getAtkSpd());
        System.out.println("0.25 의 필요 공격속도 = " + hunter1.getAtkSpd(0.25));
        System.out.println("1.27 의 필요 공격속도 = " + hunter1.getAtkSpd(1.27));
    }

    @Test
    @DisplayName("무기를 보유하지 않은 헌터의 공격속도 출력")
    public void getAtkSpdTest2() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.STRONG, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDTO(0,0,0,0,0,0,0,0,0)), "");

        // when
        hunter1.setTotalSpec();

        // then
        System.out.println("헌터의 현재 공격속도 = " + hunter1.getAtkSpd());
        System.out.println("0.25 까지 도달을 위한 필요 공격속도 = " + hunter1.getAtkSpd(0.25));
    }

    private ItemOptionDTO createItemOption() {
        ItemOptionDTO itemOptionDTO = new ItemOptionDTO();
        itemOptionDTO.setOption(null);
        itemOptionDTO.setValue(0);
        return itemOptionDTO;
    }

    private ItemOptionDTO createItemOption(OptionList option, Integer value) {
        ItemOptionDTO itemOptionDTO = new ItemOptionDTO();
        itemOptionDTO.setOption(option);
        itemOptionDTO.setValue(value);
        return itemOptionDTO;
    }

    private ItemDTO createItemDTO(ItemOptionDTO option1, ItemOptionDTO option2, ItemOptionDTO option3, ItemOptionDTO option4, ItemOptionDTO option5, ItemOptionDTO option6, ItemOptionDTO option7, ItemOptionDTO option8) {
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

    private StatDTO createStatDTO(int atk, int def, int crit, int spd, int evasion, int hp, int satiety, int mood, int stamina) {
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

    private TechDTO createTechDTO(int hp, int mood_max, int satiety_max, int stamina_max, int atk, int def, int crit, int spd, int evasion, int walk, int skill1, int skill2, int mood, int satiety, int stamina) {
        TechDTO techDTO = new TechDTO();
        techDTO.setHp(hp);
        techDTO.setMood_max(mood_max);
        techDTO.setSatiety_max(satiety_max);
        techDTO.setStamina_max(stamina_max);
        techDTO.setAtk(atk);
        techDTO.setDef(def);
        techDTO.setCrit(crit);
        techDTO.setSpd(spd);
        techDTO.setEvasion(evasion);
        techDTO.setWalk(walk);
        techDTO.setSkill1(skill1);
        techDTO.setSkill2(skill2);
        techDTO.setMood(mood);
        techDTO.setSatiety(satiety);
        techDTO.setStamina(stamina);

        return techDTO;
    }

}
