package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.item.OptionList;
import lombok.Data;

@Data
public class ItemOptionDto {

    private OptionList option;
    private Integer value;

}
