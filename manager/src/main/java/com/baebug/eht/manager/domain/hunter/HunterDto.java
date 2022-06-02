package com.baebug.eht.manager.domain.hunter;

import lombok.Data;

@Data
public class HunterDto {

    private String name;

    private Characteristic characteristic;    // Enum type
    private HunterClass hunterClass;        // Enum type
    private Stat stat;                      // Embedded type
}
