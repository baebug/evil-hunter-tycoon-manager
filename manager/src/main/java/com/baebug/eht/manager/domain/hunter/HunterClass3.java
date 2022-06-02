package com.baebug.eht.manager.domain.hunter;

public enum HunterClass3 {
    BARBARIAN("바바리안"),
    SWORD_SAINT("소드세인트"),
    DESTROYER("디스트로이어"),
    MINSTREL("민스트럴"),
    SCOUT("스카우트"),
    ARCANE_ARCHER("아케인아처"),
    CONJURER("컨저러"),
    DARK_LORD("다크로드"),
    ILLUSIONIST("일루셔니스트"),
    INQUISITOR("인퀴지터"),
    GUARDIAN("가디언"),
    EXECUTOR("익스큐터");

    private String name;

    HunterClass3(String name) {
        this.name = name;
    }
}
