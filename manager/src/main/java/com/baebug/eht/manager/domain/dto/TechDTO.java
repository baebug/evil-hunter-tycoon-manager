package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.hunter.TechList;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Tech 객체의 DTO
 */
@Data
public class TechDTO {

    @NotNull
    private int hp;
    @NotNull
    private int mood_max;
    @NotNull
    private int satiety_max;
    @NotNull
    private int stamina_max;
    @NotNull
    private int atk;
    @NotNull
    private int def;
    @NotNull
    private int crit;
    @NotNull
    private int spd;
    @NotNull
    private int evasion;
    @NotNull
    private int walk;
    @NotNull
    private int skill1;
    @NotNull
    private int skill2;
    @NotNull
    private int mood;
    @NotNull
    private int satiety;
    @NotNull
    private int stamina;

}
