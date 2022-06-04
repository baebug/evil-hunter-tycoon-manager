package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DungeonBuff {

    private int floor;

    public void setDungeonBuff(int floor) {
        this.floor = floor;
    }

    public void calculate(SpecDto specDto) throws IllegalAccessException {
        int tmp = 0;
        if (getFloor() > 125) tmp = getFloor() - 125;

        specDto.add("atk", tmp * .5);
        specDto.add("def", tmp * .5);
    }


}
