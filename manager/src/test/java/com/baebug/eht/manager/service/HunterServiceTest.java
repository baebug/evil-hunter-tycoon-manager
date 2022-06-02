package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.HunterDto;
import com.baebug.eht.manager.domain.hunter.Stat;
import com.baebug.eht.manager.repository.HunterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HunterServiceTest {

    @Autowired HunterService hunterService;
    @Autowired HunterRepository hunterRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("헌터 추가")
    public void inviteHunter() throws Exception {
        // given
        HunterDto hunter1 = createHunterDto("헌터A");

        // when
        Long savedId = hunterService.join(hunter1);

        // then
        assertEquals(1, hunterService.findHunters().size());
    }

    @Test
    @DisplayName("헌터 수정")
    public void updateHunter() throws Exception {
        // given
        HunterDto hunter1 = createHunterDto("헌터A");
        HunterDto hunter2 = createHunterDto("헌터B");

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
        HunterDto hunter1 = createHunterDto("헌터A");
        HunterDto hunter2 = createHunterDto("헌터B");

        Long savedId = hunterService.join(hunter1);
        hunterService.join(hunter2);

        // when
        hunterService.exile(hunterService.findHunter(savedId));

        // then
        assertEquals(1, hunterService.findHunters().size());
    }

    private HunterDto createHunterDto(String name) {
        HunterDto hunterDto = new HunterDto();
        hunterDto.setName(name);
        hunterDto.setCharacteristic(Characteristic.Strong);
        hunterDto.setHunterClass(HunterClass.BERSERKER);
        hunterDto.setStat(new Stat(1, 1, 1, 1, 1, 1, 1, 1, 1));

        return hunterDto;
    }

}