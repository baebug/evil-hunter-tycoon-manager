package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.SpecDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class CommonBuff {

    private final GuildBuff guild;
    private final DungeonBuff dungeon;
    private final BuildingBuff building;
    private final CollectionBasicBuff collectionBasic;
    private final CollectionSetBuff collectionSet;

    public SpecDto calculate() throws IllegalAccessException {
        SpecDto specDto = new SpecDto();

        getGuild().calculate(specDto);
        getDungeon().calculate(specDto);
        getBuilding().calculate(specDto);
        getCollectionBasic().calculate(specDto);
        getCollectionSet().calculate(specDto);

        return specDto;
    }

}
