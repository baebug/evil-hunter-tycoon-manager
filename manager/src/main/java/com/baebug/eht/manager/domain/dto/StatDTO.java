package com.baebug.eht.manager.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatDTO {

    @NotNull
    private Integer atk = 0;
    @NotNull
    private Integer def = 0;
    @NotNull
    private Integer crit = 0;
    @NotNull
    private Integer spd = 0;
    @NotNull
    private Integer evasion = 0;
    @NotNull
    private Integer hp = 0;
    @NotNull
    private Integer satiety = 0;
    @NotNull
    private Integer mood = 0;
    @NotNull
    private Integer stamina = 0;

}
