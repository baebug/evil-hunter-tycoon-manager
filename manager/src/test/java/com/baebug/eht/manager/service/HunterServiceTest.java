package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.dto.*;
import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import com.baebug.eht.manager.domain.item.ItemOption;
import com.baebug.eht.manager.domain.item.OptionList;
import com.baebug.eht.manager.repository.HunterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("통합 테스트")
class HunterServiceTest {

    @Autowired HunterService hunterService;
    @Autowired HunterRepository hunterRepository;
    @Autowired CommonBuff commonBuff;
    @Autowired ItemService itemService;

    @Test
    public void 헌터_추가() throws Exception {
        // given
        HunterDTO hunter1 = createHunterDTO("헌터A");

        // when
        Long savedId = hunterService.join(hunter1);

        // then
        assertEquals(2, hunterService.findHunters().size());
    }

    @Test
    public void 헌터_수정() throws Exception {
        // given
        HunterDTO hunter1 = createHunterDTO("헌터A");
        HunterDTO hunter2 = createHunterDTO("헌터B");

        Long savedId = hunterService.join(hunter1);

        // when
        hunterService.update(savedId, hunter2);

        // then
        assertEquals(hunter2.getName(), hunterService.findHunter(savedId).getName());
    }

    @Test
    public void 헌터_추방() throws Exception {
        // given
        HunterDTO hunter1 = createHunterDTO("헌터A");
        HunterDTO hunter2 = createHunterDTO("헌터B");

        Long savedId = hunterService.join(hunter1);
        hunterService.join(hunter2);

        // when
        hunterService.exile(savedId);

        // then
        assertEquals(2, hunterService.findHunters().size());
    }

    @Test
    public void 공용_버프_확인() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.OTHERS, new HunterClass(new HunterClassDTO()), new StatEntity(createStatDto(0,0,0,0,0,0,0,0, 0)), "");


        SpecDTO totalSpec = hunterService.getTotalSpec(hunter);
        System.out.println("totalSpec.getAtk() = " + totalSpec.getAtk());

        setCommonBuff();
        hunterService.getTotalSpec(hunter);
        System.out.println("totalSpec.getAtk() = " + totalSpec.getAtk());

        hunter.getTotalSpec().clear();
        System.out.println("totalSpec.getAtk() = " + totalSpec.getAtk());

    }

    @Test
    public void 헌터_장비_조회() throws Exception {
        // given
        Long hunterId = hunterService.join(createHunterDTO("헌터A"));
        Hunter hunter = hunterService.findHunter(hunterId);
        ItemDTO weaponDTO = createItemDTO(1.8, Arrays.asList(new ItemOption(OptionList.ATK, 10)));
        itemService.update(hunter.getEquipment().getWeapon().getId(), weaponDTO);

        // when
        HunterItemDTO items = hunterService.getItems(hunterId);

        // then
        assertEquals(weaponDTO.getAtk_spd(), items.getWeapon().getAtk_spd());
    }

    private HunterDTO createHunterDTO(String name) {
        HunterDTO hunterDto = new HunterDTO();
        hunterDto.setName(name);
        hunterDto.setCharacteristic(Characteristic.STRONG);
        hunterDto.setHunterClass(new HunterClassDTO());
        hunterDto.setStat(createStatDto(0,0,0,0,0,0,0,0,0));


        return hunterDto;
    }

    private ItemDTO createItemDTO(double atk_spd, List<ItemOption> options) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setAtk_spd(atk_spd);

        return itemDTO;
    }

    private ItemDTO createItemDTO(List<ItemOption> options) {
        ItemDTO itemDTO = new ItemDTO();

        return itemDTO;
    }

    private void setCommonBuff() {
        CommonBuffDTO commonBuffDTO = new CommonBuffDTO();
        GuildBuffDTO guildBuffDTO = new GuildBuffDTO();
        guildBuffDTO.setAtk(15);
        commonBuffDTO.setGuild(guildBuffDTO);
        commonBuffDTO.setCollectionBasic(new CollectionBasicBuffDTO());
        commonBuffDTO.setCollectionSet(new CollectionSetBuffDTO());
        commonBuff.setCommonBuff(commonBuffDTO);
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