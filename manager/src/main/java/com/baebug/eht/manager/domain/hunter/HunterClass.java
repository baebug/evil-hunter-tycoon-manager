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
    BERSERKER("버서커", Arrays.asList(DUELIST, SLAYER, WARRIOR), Arrays.asList(BARBARIAN, SWORD_SAINT, DESTROYER)),
    RANGER("레인저", Arrays.asList(HAWK_EYE, SNIPER, SUMMONIC_ARCHER), Arrays.asList(MINSTREL, SCOUT, ARCANE_ARCHER)),
    PALADIN("팔라딘", Arrays.asList(TEMPLAR, CRUSADER, DARK_PALADIN), Arrays.asList(INQUISITOR, GUARDIAN, EXECUTOR)),
    SORCERER("소서러", Arrays.asList(ARCH_MAGE, DARK_MAGE, IGNIS), Arrays.asList(CONJURER, DARK_LORD, ILLUSIONIST));

    private final String desc;
    private final List<HunterClass2> second;
    private final List<HunterClass3> third;

    HunterClass(String desc, List<HunterClass2> second, List<HunterClass3> third) {
        this.desc = desc;
        this.second = second;
        this.third = third;
    }
}
