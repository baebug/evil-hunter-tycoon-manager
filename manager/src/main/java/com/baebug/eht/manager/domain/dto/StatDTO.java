package com.baebug.eht.manager.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatDTO {

    @NotNull
    private Integer atk;
    @NotNull
    private Integer def;
    @NotNull
    private Integer crit;
    @NotNull
    private Integer spd;
    @NotNull
    private Integer evasion;
    @NotNull
    private Integer hp;
    @NotNull
    private Integer satiety;
    @NotNull
    private Integer mood;
    @NotNull
    private Integer stamina;

}
