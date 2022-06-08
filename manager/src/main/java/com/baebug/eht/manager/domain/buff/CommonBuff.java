package com.baebug.eht.manager.domain.buff;

import com.baebug.eht.manager.domain.dto.CommonBuffDTO;
import com.baebug.eht.manager.domain.dto.SpecDTO;
import com.baebug.eht.manager.domain.hunter.Hunter;
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
//    private final DungeonBuff dungeon;
//    private final BuildingBuff building;
    private final CollectionBasicBuff collectionBasic;
    private final CollectionSetBuff collectionSet;

    public void setCommonBuff(CommonBuffDTO commonBuffDTO) {
        this.guild.setGuildBuff(commonBuffDTO.getGuild());
        this.collectionBasic.setCollectionBasicBuff(commonBuffDTO.getCollectionBasic());
        this.collectionSet.setCollectionSetBuff(commonBuffDTO.getCollectionSet());
    }

    /**
     * hunter 의 spec 에 마을 버프로 오른 능력치를 합산한다.
     * @param hunter    대상 헌터
     */
    public void calculate(Hunter hunter) throws IllegalAccessException {
        SpecDTO spec = hunter.getTotalSpec();

        getGuild().calculate(spec);
        getCollectionBasic().calculate(spec);
        getCollectionSet().calculate(spec);
    }

}
