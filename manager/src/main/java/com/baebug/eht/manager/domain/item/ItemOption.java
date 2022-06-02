package com.baebug.eht.manager.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOption {

    private OptionList option;
    private int value;

    public ItemOption(OptionList option, int value) {
        this.option = option;
        this.value = value;
    }
}
