package com.baebug.eht.manager.domain.hunter;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.baebug.eht.manager.domain.hunter.HunterClass2.*;
import static com.baebug.eht.manager.domain.hunter.HunterClass3.*;

/**
 * 헌터의 직업
 */
@Getter
public enum HunterClass {
    BERSERKER(Arrays.asList(DUELIST, SLAYER, WARRIOR), Arrays.asList(BARBARIAN, SWORD_SAINT, DESTROYER)),
    RANGER(Arrays.asList(HAWK_EYE, SNIPER, SUMMONIC_ARCHER), Arrays.asList(MINSTREL, SCOUT, ARCANE_ARCHER)),
    PALADIN(Arrays.asList(TEMPLAR, CRUSADER, DARK_PALADIN), Arrays.asList(INQUISITOR, GUARDIAN, EXECUTOR)),
    SORCERER(Arrays.asList(ARCH_MAGE, DARK_MAGE, IGNIS), Arrays.asList(CONJURER, DARK_LORD, ILLUSIONIST));

    private final List<HunterClass2> second;
    private final List<HunterClass3> third;

    HunterClass(List<HunterClass2> second, List<HunterClass3> third) {
        this.second = second;
        this.third = third;
    }
}
