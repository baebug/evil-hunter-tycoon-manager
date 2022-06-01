package com.baebug.eht.manager.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {

    @Test
    public void update() throws Exception {
        // given
        Hunter hunter1 = new Hunter("헌터A", Characteristic.Strong, HunterClass.Warrior, new Stat());
        Hunter hunter2 = new Hunter("헌터B", Characteristic.Swift, HunterClass.Mage, new Stat());

        // when
        hunter1.changeHunter(hunter2.getName(), hunter2.getCharacteristic(), hunter2.getHunterClass(), hunter2.getStat());

        // then
        assertEquals(hunter2.getName(), hunter1.getName());
        assertEquals(hunter2.getCharacteristic(), hunter1.getCharacteristic());
        assertEquals(hunter2.getHunterClass(), hunter1.getHunterClass());
        assertEquals(hunter2.getStat(), hunter1.getStat());
    }

}