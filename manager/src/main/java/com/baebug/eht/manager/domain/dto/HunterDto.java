package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.Stat;
import lombok.Data;

@Data
public class HunterDto {

    private String name;

    private Characteristic characteristic;    // Enum type
    private HunterClass hunterClass;        // Enum type
    private Stat stat;                      // Embedded type
}
