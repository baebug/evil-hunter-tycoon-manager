package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.hunter.Characteristic;
import com.baebug.eht.manager.domain.hunter.HunterClass;
import com.baebug.eht.manager.domain.hunter.StatEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Hunter 객체의 DTO
 */
@Data
public class HunterDTO {

    @NotBlank
    private String name;
    @Size(max = 10)
    private String desc;

    @NotNull
    private Characteristic characteristic;
    @NotNull
    private HunterClass hunterClass;
    private StatDTO stat = new StatDTO();

    public HunterDTO() {
    }

}
