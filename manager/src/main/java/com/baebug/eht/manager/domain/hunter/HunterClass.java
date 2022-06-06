package com.baebug.eht.manager.domain.hunter;

import com.baebug.eht.manager.domain.dto.HunterClassDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 헌터의 직업
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HunterClass {

    @Enumerated(EnumType.STRING)
    private HunterClassList1 firstClass;
    @Enumerated(EnumType.STRING)
    private HunterClassList2 secondClass;
    @Enumerated(EnumType.STRING)
    private HunterClassList3 thirdClass;

    public HunterClass(HunterClassDTO hunterClassDTO) {
        this.firstClass = hunterClassDTO.getFirst();
        this.secondClass = hunterClassDTO.getSecond();
        this.thirdClass = hunterClassDTO.getThird();
    }
}
