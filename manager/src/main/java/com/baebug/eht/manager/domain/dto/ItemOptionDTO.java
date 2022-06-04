package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.item.OptionList;
import lombok.Data;

/**
 * ItemOption 객체의 DTO
 */
@Data
public class ItemOptionDTO {

    private OptionList option;
    private Integer value;

}
