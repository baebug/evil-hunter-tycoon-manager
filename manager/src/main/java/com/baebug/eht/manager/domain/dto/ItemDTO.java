package com.baebug.eht.manager.domain.dto;

import com.baebug.eht.manager.domain.item.ItemOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Item 객체의 DTO
 */
@Data
public class ItemDTO {

    private Double atk_spd;
    private ItemOptionDTO option1 = new ItemOptionDTO();
    private ItemOptionDTO option2 = new ItemOptionDTO();
    private ItemOptionDTO option3 = new ItemOptionDTO();
    private ItemOptionDTO option4 = new ItemOptionDTO();
    private ItemOptionDTO option5 = new ItemOptionDTO();
    private ItemOptionDTO option6 = new ItemOptionDTO();
    private ItemOptionDTO option7 = new ItemOptionDTO();
    private ItemOptionDTO option8 = new ItemOptionDTO();

}
