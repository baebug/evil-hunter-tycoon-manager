package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 공용 버프의 집합 클래스
 */
@Component
@RequiredArgsConstructor
@Getter
public class CommonBuff {

    /**
     * 공용 버프: 연합, 지하던전, 건물, 도감 버프
     * RequiredArgsConstructor 어노테이션을 통해 DI 받고 싱글톤으로 관리한다.
     */
    private final GuildBuff guild;
    private final DungeonBuff dungeon;
    private final BuildingBuff building;
    private final CollectionBasicBuff collectionBasic;
    private final CollectionSetBuff collectionSet;

    /**
     * 새로운 SpecDTO 인스턴스를 생성하여 능력치를 합산한 후 반환한다.
     * @return      합산한 공용 버프 능력치를 가진 SpecDTO
     */
    public SpecDTO calculate() throws IllegalAccessException {
        SpecDTO specDTO = new SpecDTO();

        getGuild().calculate(specDTO);
        getDungeon().calculate(specDTO);
        getBuilding().calculate(specDTO);
        getCollectionBasic().calculate(specDTO);
        getCollectionSet().calculate(specDTO);

        return specDTO;
    }

}
