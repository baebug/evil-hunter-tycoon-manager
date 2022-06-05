package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.hunter.TechList;
import lombok.Data;

/**
 * Tech 객체의 DTO
 */
@Data
public class TechDTO {

    private TechList option;
    private int value;

}
