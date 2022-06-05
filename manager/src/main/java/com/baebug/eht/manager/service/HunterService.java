package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.dto.HunterItemDTO;
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
     * hunterDTO 를 바탕으로 Hunter 객체를 생성하고 저장한다.
     * @param hunterDto     생성할 헌터의 정보
     * @return              생성된 헌터의 id 값
     */
    @Transactional
    public Long join(HunterDTO hunterDto) throws IllegalAccessException {
        Hunter hunter = Hunter.createHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterDto.getHunterClass(), hunterDto.getStat());
        hunterRepository.save(hunter);
        return hunter.getId();
    }

    /**
     * hunterDTO 를 바탕으로 해당 Hunter 객체를 업데이트 한다.
     * @param hunterId      대상 헌터의 id 값
     * @param hunterDto     업데이트 할 정보
     */
    @Transactional
    public void update(Long hunterId, HunterDTO hunterDto) throws IllegalAccessException {
        Hunter hunter = hunterRepository.findById(hunterId);
        hunter.changeHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterDto.getHunterClass(), hunterDto.getStat());
    }

    /**
     * 해당 Hunter 객체를 삭제한다.
     * @param hunterId      대상 헌터의 id 값
     */
    @Transactional
    public void exile(Long hunterId) {
        Hunter hunter = hunterRepository.findById(hunterId);
        hunterRepository.remove(hunter);
    }


    /**
     * 해당 id 값을 가진 헌터를 조회한다.
     * @param hunterId      조회할 헌터의 id 값
     * @return              조회된 Hunter 객체
     */
    public Hunter findHunter(Long hunterId) {
        return hunterRepository.findById(hunterId);
    }

    /**
     * 모든 Hunter 객체를 조회한다.
     * @return              모든 Hunter 객체를 List 에 담아서 반환
     */
    public List<Hunter> findHunters() {
        return hunterRepository.findAll();
    }

    /**
     * 해당 직업을 가진 헌터를 조회한다.
     * @param hunterClass   조회할 헌터의 직업
     * @return              해당 직업을 가진 Hunter 객체를 List 에 담아서 반환
     */
    public List<Hunter> findHuntersByClass(HunterClass hunterClass) {
        return hunterRepository.findByClass(hunterClass);
    }

    /**
     * 헌터의 장비 객체를 반환하는 메서드
     * @param hunterId      조회할 헌터의 id 값
     * @return              헌터의 장비가 담긴 DTO
     */
    public HunterItemDTO getItems(Long hunterId) {
        HunterItemDTO hunterItemDTO = new HunterItemDTO();
        Hunter hunter = hunterRepository.findById(hunterId);
        hunterItemDTO.setEquipment(hunter.getEquipment());

        return hunterItemDTO;
    }

    /**
     * 헌터의 종합 능력치를 계산한다.
     * @param hunter        대상 헌터 객체
     */
    public void calculate(Hunter hunter) throws IllegalAccessException {
        SpecDTO commonSpec = commonBuff.calculate();
        hunter.calculate(commonSpec);
    }


}
