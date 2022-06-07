package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.Getter;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * 무기 객체
 * 무기 공격속도 옵션을 위해 카테고리를 분류했다.
 */
@Entity
@Getter
public class Weapon extends Item {

    /**
     * 무기 공격속도
     */
    private double atk_spd;

    public void setAtk_spd(double atk_spd) {
        this.atk_spd = atk_spd;
    }

    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        super.calculate(specDTO);
        specDTO.add("atk_spd", atk_spd);
    }
}
