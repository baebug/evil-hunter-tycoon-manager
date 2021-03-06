package com.baebug.eht.manager.service;

import com.baebug.eht.manager.domain.buff.CommonBuff;
import com.baebug.eht.manager.domain.dto.*;
import com.baebug.eht.manager.domain.hunter.Hunter;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import com.baebug.eht.manager.domain.hunter.TechEntity;
import com.baebug.eht.manager.domain.item.Equipment;
import com.baebug.eht.manager.domain.item.Item;
import com.baebug.eht.manager.repository.HunterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        StatEntity stat = new StatEntity(hunterDto.getStat());
        HunterClass hunterClass = new HunterClass(hunterDto.getHunterClass());
        Hunter hunter = Hunter.createHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterClass, stat, hunterDto.getDesc());
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
        StatEntity stat = new StatEntity(hunterDto.getStat());
        HunterClass hunterClass = new HunterClass(hunterDto.getHunterClass());
        Hunter hunter = hunterRepository.findById(hunterId);
        hunter.changeHunter(hunterDto.getName(), hunterDto.getCharacteristic(), hunterClass, stat, hunterDto.getDesc());
    }

    @Transactional
    public void updateTech(Long hunterId, TechDTO techDTO) throws IllegalAccessException {
        TechEntity tech = new TechEntity(techDTO);
        Hunter hunter = hunterRepository.findById(hunterId);
        hunter.setTech(tech);
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
     * 헌터의 장비 능력치를 계산한다.
     * @param hunter        대상 헌터 객체
     */
    public Map<String, SpecDTO> getEquipmentSpec(Hunter hunter) throws IllegalAccessException {
        Map<String, SpecDTO> equipmentMap = new HashMap<>();
        equipmentMap.put("equipment", hunter.getEquipmentSpec());
        Equipment equipment = hunter.getEquipment();
        for (Field field : equipment.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Item item = (Item) field.get(equipment);
            equipmentMap.put(field.getName(), item.getItemSpec());
        }
        return equipmentMap;
    }

    /**
     * 헌터의 종합 능력치를 계산한다.
     * @param hunter        대상 헌터 객체
     */
    public SpecDTO getTotalSpec(Hunter hunter) throws IllegalAccessException {
        SpecDTO totalSpec = hunter.getTotalSpec();
        SpecDTO commonSpec = commonBuff.getCommonSpec();
        totalSpec.add(commonSpec);

        return totalSpec;
    }

    public Map<String, SpecDTO> getSpecMap(Hunter hunter) throws IllegalAccessException {
        Map<String, SpecDTO> specMap = new HashMap<>();
        specMap.put("statSpec", hunter.getStatSpec());
        specMap.put("characteristicSpec", hunter.getCharacteristicSpec());
        specMap.put("techSpec", hunter.getTechSpec());
        specMap.put("equipmentSpec", hunter.getEquipmentSpec());
        specMap.put("guildSpec", commonBuff.getGuildSpec());
        specMap.put("collectionSpec", commonBuff.getCollectionSpec());
        specMap.put("totalSpec", getTotalSpec(hunter));

        return specMap;
    }


    public List<Double> getAtkSpd(Hunter hunter) throws IllegalAccessException {
        SpecDTO totalSpec = getTotalSpec(hunter);

        double atkSpd = hunter.getAtkSpd(totalSpec);
        double atkSpdHalf = hunter.getAtkSpd(totalSpec, 0.5);
        double atkSpdQuarter = hunter.getAtkSpd(totalSpec, 0.25);

        return List.of(atkSpd, atkSpdHalf, atkSpdQuarter);
    }


    public CommonBuff getCommonBuff() {
        return commonBuff;
    }

    public void setCommonBuff(CommonBuffDTO commonBuffDTO) {
        commonBuff.setCommonBuff(commonBuffDTO);
    }




}
