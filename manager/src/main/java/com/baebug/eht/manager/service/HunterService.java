package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.dto.SpecDTO;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.dto.HunterDTO;
import com.baebug.eht.manager.repository.HunterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HunterService {

    private final HunterRepository hunterRepository;
    private final CommonBuff commonBuff;

    /**
     * Hunter 추가, 수정, 삭제
     * DTO 가 넘어오면 Hunter 객체로 만들어서 Repository 로 넘겨준다.
     */
    @Transactional
    public Long join(HunterDTO hunterDto) throws Exception {
        Hunter hunter = Hunter.createHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterDto.getHunterClass(), hunterDto.getStat());
        hunterRepository.save(hunter);
        return hunter.getId();
    }

    @Transactional
    public void update(Long hunterId, HunterDTO hunterDto) throws Exception {
        Hunter hunter = hunterRepository.findById(hunterId);
        hunter.changeHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterDto.getHunterClass(), hunterDto.getStat());
    }

    @Transactional
    public void exile(Hunter hunter) {
        hunterRepository.remove(hunter);
    }


    /**
     * Hunter 조회
     * 1명, 전체, 직업
     */
    public Hunter findHunter(Long hunterId) {
        return hunterRepository.findById(hunterId);
    }

    public List<Hunter> findHunters() {
        return hunterRepository.findAll();
    }

    public List<Hunter> findHuntersByClass(HunterClass hunterClass) {
        return hunterRepository.findByClass(hunterClass);
    }

    /**
     * 공용 버프로 증가하는 능력치 합산 후
     * 헌터 객체 능력치 합산
     */
    public void calculate(Hunter hunter) throws IllegalAccessException {
        SpecDTO commonSpec = commonBuff.calculate();
        hunter.calculate(commonSpec);
    }


}
