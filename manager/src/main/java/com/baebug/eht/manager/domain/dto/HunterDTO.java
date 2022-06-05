package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import lombok.Data;

/**
 * Hunter 객체의 DTO
 */
@Data
public class HunterDTO {

    private String name;

    private Characteristic characteristic;
    private HunterClass hunterClass;
    private StatEntity stat;
    private String desc;

    public HunterDTO() {
    }

    public HunterDTO(String name, Characteristic characteristic, HunterClass hunterClass, StatEntity stat, String desc) {
        this.name = name;
        this.characteristic = characteristic;
        this.hunterClass = hunterClass;
        this.stat = stat;
        this.desc = desc;
    }
}
