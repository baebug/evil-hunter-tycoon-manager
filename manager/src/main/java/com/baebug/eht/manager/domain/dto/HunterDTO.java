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

    private Characteristic characteristic;    // Enum type
    private HunterClass hunterClass;        // Enum type
    private StatEntity stat;                      // Embedded type
}
