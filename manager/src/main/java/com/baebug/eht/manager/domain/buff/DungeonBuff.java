package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 지하던전 버프 클래스
 */
@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DungeonBuff {

    /**
     * 지하던전 최대 층 수
     */
    private int floor;

    /**
     * 지하던전 최대 층 수를 입력받는 메서드
     * @param floor     지하던전 최대 층 수
     */
    public void setDungeonBuff(int floor) {
        this.floor = floor;
    }

    /**
     * 층 수에 비례하여 공격력, 방어력 옵션을 합산한다.
     * 0.5 * (층 수 - 125)
     */
    public void calculate(SpecDTO specDTO) throws IllegalAccessException {
        int tmp = 0;
        if (getFloor() > 125) tmp = getFloor() - 125;

        specDTO.add("atk", tmp * .5);
        specDTO.add("def", tmp * .5);
    }
}
