package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.Getter;

/**
 * 능력치에 영항을 미치는 성격들
 * 나머지는 OTHERS
 */
@Getter
public enum Characteristic {
    STRONG("힘이 쎈", "atk", 10),
    FRAGILE("허약한", "atk", -10),
    SWIFT("날쌘돌이", "spd", 10),
    THICKHEADED("둔한", "spd", -10),
    CHARISMATIC("리더쉽이 강한", "party", null),
    DEADWEIGHT("고문관", "party", null),
    HEROIC("영웅심리", "heroic", 7),
    MAN_OF_STEEL("금강불괴", "def", 10),
    NIMBLE("쏜살같은", "evasion", 3),
    SLUGGISH("굼뜬", "evasion", -3),
    SHARP("샤프한", "crit", 3),
    DULL("둔탁한", "crit", -3),
    OTHERS("기타", null, null);

    /**
     * characteristic: 한글명
     */
    private final String characteristic;
    private final String option;
    private final Integer value;

    Characteristic(String characteristic, String option, Integer value) {
        this.characteristic = characteristic;
        this.option = option;
        this.value = value;
    }

    /**
     * 성격에 해당하는 능력치를 합산한다.
     */
    public SpecDTO calculate() throws IllegalAccessException {
        SpecDTO specDTO = new SpecDTO();

        if (getValue() != null)
            if (getOption() != null) {
                specDTO.add(getOption(), (double) getValue());
            }

        return specDTO;
    }

}
