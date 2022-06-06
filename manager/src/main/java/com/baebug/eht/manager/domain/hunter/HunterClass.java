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
        this.firstClass = hunterClassDTO.getFirstClass();
        this.secondClass = hunterClassDTO.getSecondClass();
        this.thirdClass = hunterClassDTO.getThirdClass();
    }

    public String topClassName() {
        if (this.getThirdClass() != null) return this.getThirdClass().getName();
        else if (this.getSecondClass() != null) return this.getSecondClass().getName();
        return this.getFirstClass().getName();
    }
}
