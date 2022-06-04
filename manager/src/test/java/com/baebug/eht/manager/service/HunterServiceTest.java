package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.dto.HunterDTO;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import com.baebug.eht.manager.repository.HunterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("통합 테스트")
class HunterServiceTest {

    @Autowired HunterService hunterService;
    @Autowired HunterRepository hunterRepository;
    @Autowired CommonBuff commonBuff;

    @Test
    @DisplayName("헌터 추가")
    public void inviteHunter() throws Exception {
        // given
        HunterDTO hunter1 = createHunterDto("헌터A");

        // when
        Long savedId = hunterService.join(hunter1);

        // then
        assertEquals(1, hunterService.findHunters().size());
    }

    @Test
    @DisplayName("헌터 수정")
    public void updateHunter() throws Exception {
        // given
        HunterDTO hunter1 = createHunterDto("헌터A");
        HunterDTO hunter2 = createHunterDto("헌터B");

        Long savedId = hunterService.join(hunter1);

        // when
        hunterService.update(savedId, hunter2);

        // then
        assertEquals(hunter2.getName(), hunterService.findHunter(savedId).getName());
    }

    @Test
    @DisplayName("헌터 추방")
    public void exileHunter() throws Exception {
        // given
        HunterDTO hunter1 = createHunterDto("헌터A");
        HunterDTO hunter2 = createHunterDto("헌터B");

        Long savedId = hunterService.join(hunter1);
        hunterService.join(hunter2);

        // when
        hunterService.exile(hunterService.findHunter(savedId));

        // then
        assertEquals(1, hunterService.findHunters().size());
    }

    @Test
    @DisplayName("공용 버프 확인")
    public void commonBuffTest() throws Exception {
        // given
        Hunter hunter = new Hunter("헌터A", Characteristic.OTHERS, HunterClass.BERSERKER, new StatEntity(0, 0, 0, 0, 0, 0, 0, 0, 0));

        hunterService.calculate(hunter);
        System.out.println("hunter.getSpec().getAtk() = " + hunter.getSpec().getAtk());

        setCommonBuff();
        hunterService.calculate(hunter);
        System.out.println("hunter.getSpec().getAtk() = " + hunter.getSpec().getAtk());

    }

    private HunterDTO createHunterDto(String name) {
        HunterDTO hunterDto = new HunterDTO();
        hunterDto.setName(name);
        hunterDto.setCharacteristic(Characteristic.STRONG);
        hunterDto.setHunterClass(HunterClass.BERSERKER);
        hunterDto.setStat(new StatEntity(1, 1, 1, 1, 1, 1, 1, 1, 1));

        return hunterDto;
    }

    private void setCommonBuff() {
        commonBuff.getGuild().setGuildBuff(15, 0, 15, 5, 5);
        commonBuff.getDungeon().setDungeonBuff(200);
        commonBuff.getBuilding().setBuildingBuff(300, 300, 300, 300, 300, 300);
    }

}