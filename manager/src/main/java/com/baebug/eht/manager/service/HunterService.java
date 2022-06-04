package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.dto.SpecDto;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.dto.HunterDto;
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
     * Dto 가 넘어오면 Hunter 객체로 만들어서 Repository 로 넘겨준다.
     * 대신 앞단을 만들어야 Dto 가 어떻게 생긴지 알겠지?
     */
    @Transactional
    public Long join(HunterDto hunterDto) throws IllegalAccessException {
        Hunter hunter = Hunter.createHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterDto.getHunterClass(), hunterDto.getStat());
        hunterRepository.save(hunter);
        return hunter.getId();
    }

    @Transactional
    public void update(Long hunterId, HunterDto hunterDto) throws IllegalAccessException {
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
     * 스펙 계산하기
     */
    public void calculate(Hunter hunter) throws IllegalAccessException {
        SpecDto commonSpec = commonBuff.calculate();
        hunter.calculate(commonSpec);
    }
}
