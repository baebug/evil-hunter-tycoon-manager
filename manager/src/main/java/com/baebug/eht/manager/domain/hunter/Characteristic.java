package com.baebug.eht.manager.domain.hunter;

public enum Characteristic {
    Strong("힘이 쎈", "atk", 10),
    Fragile("허약한", "atk", -10),
    Swift("날쌘돌이", "spd", 10),
    Thickheaded("둔한", "spd", -10),
    Charismatic("리더쉽이 강한", "party", null),
    DeadWeight("고문관", "party", null),
    Heroic("영웅심리", "heroic", 7),
    ManOfSteel("금강불괴", "def", 10),
    Nimble("쏜살같은", "evasion", 3),
    Sluggish("굼뜬", "evasion", -3),
    Sharp("샤프한", "crit", 3),
    Dull("둔탁한", "crit", -3),
    Others("기타", null, null);

    private final String characteristic;
    private final String option;
    private final Integer value;

    Characteristic(String characteristic, String option, Integer value) {
        this.characteristic = characteristic;
        this.option = option;
        this.value = value;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getOption() {
        return option;
    }

    public Integer getValue() {
        return value;
    }
}
