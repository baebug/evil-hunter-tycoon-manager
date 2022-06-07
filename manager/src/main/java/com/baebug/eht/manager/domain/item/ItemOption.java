package com.baebug.eht.manager.domain.item;

import com.baebug.eht.manager.domain.dto.HunterClassDTO;
import com.baebug.eht.manager.domain.dto.ItemOptionDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * itemOption 객체
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOption {

    @Enumerated(EnumType.STRING)
    private OptionList option;
    private int value;

    public ItemOption(OptionList option, int value) {
        this.option = option;
        this.value = value;
    }

    public void setOption(ItemOptionDTO itemOptionDTO) {
        this.option = itemOptionDTO.getOption();
        this.value = itemOptionDTO.getValue();
    }
}
